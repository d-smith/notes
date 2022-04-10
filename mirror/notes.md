## Mirror Protocol

mirror protocol whitepaper - https://docsend.com/view/kcsm42mqiyu5t6ej

Protocol participants - see [https://github.com/Mirror-Protocol/docs/blob/master/protocol/synopsis.md]

* Trader
    * Buy and sell mAssets through terraswap
* Minter and shorter
    * Mint tokens of an mAsset via entering into a collateralized debt position (CDP)
    * Shorter is a user that enters into the same CDP to sell the tokens immediately and get newly minder sLP tokens - sLP tokens can be staked to earn MIR reward when there is a price premium for the terraswap price copared to the oracle price
* Liquidity provider - adds equal amounts of an mAsset and UST to the corresponding terraswap pool
    * Adding to the liqudity pool rewards the provider with newly minted LP tokens which represent the LP's shared in the pool, and also provide rewards from the pool's trading fees. LP tokens can be burned to reclaim the share of mAssets and UST from the pool
* Staker - user the stakes either LP or sLP tokens with the staking contract, or MIR tokens with the gov contract in order to earn staking rewards as MIR tokens.
    * LP andd sLP token stakers earn rewards from new MIR tokens from inflation, MIR token stakers earn staking rewards from CDP withdrawal fees

More on the protocol [here](https://www.figment.io/resources/earning-and-claiming-mir-as-a-luna-staker-on-mirror-protocol#:~:text=Additional%20MIR%20can%20be%20earned%20by%3A&text=Users%20staking%20LUNA%2C%20LP%20tokens,LP%20token%20and%20MIR%20stakers.)


Terraswap

* [Home page](https://terraswaps.io/?url=https%3A%2F%2Fterraswap.io%2F)
* [The docs](https://docs.terraswap.io/)

Pool mechanism - [constant product](https://docs.terraswap.io/docs/introduction/mechanism/)

For a mirror protocol you need a set of assets that can be priced via an oracle, the ability to mint and burn tokens for an asset as users move into/out of CDPs, an automated market maker/dex to exchange tokens, and a stablecoin to stablecoin/assets token