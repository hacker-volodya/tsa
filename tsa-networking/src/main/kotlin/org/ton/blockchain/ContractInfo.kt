package org.ton.blockchain

import org.ton.boc.BagOfCells
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

data class JettonContractInfo(
    val masterState: ContractState,
    val walletContractBytesHex: String,
    val declaredOwner: String?,
    val declaredMintable: Boolean,
    val declaredTotalSupply: String,
) {
    @OptIn(ExperimentalStdlibApi::class)
    val walletContractBytes: ByteArray
        get() = walletContractBytesHex.hexToByteArray()

    @OptIn(ExperimentalEncodingApi::class)
    val jettonWalletCodeHashBase64: String
        get() = Base64.Default.encode(BagOfCells(walletContractBytes).roots.first().hash().toByteArray())
}

data class ContractState(
    val dataHex: String,
    val codeHex: String,
    val balance: Long,
)

data class JettonWalletInfo(
    val address: String,
    val owner: String,
    val jettonBalance: String,
    val state: ContractState
)

const val getWalletAddressGetMethodName = "get_wallet_address"
const val getJettonDataGetMethodName = "get_jetton_data"
const val getWalletDataGetMethodName = "get_wallet_data"
