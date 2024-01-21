# Note from [The Future of Wealth Management](https://www.jpmorgan.com/onyx/documents/portfolio-management-powered-by-tokenization.pdf)

Themes

* Blockchain tech, tokenization, and smart contracts will enable new ways of managed solutions for building and managing discretionary portfolios.
* Accelerate semi-liqued product structures for alts exposure `with the development of new
capabilities in operational processing, availability of secondary liquidity by tokenizing fund positions on a shared
ledger system, availability of detailed reporting and consolidation of fragmented investor ownership registries
(which will continue to fragment with the emergence of tokenization across new blockchain network registries)`



> Alternative investments (“alts”), such as private equity, private credit, real estate, and infrastructure, can offer
attractive risk-adjusted returns as well as portfolio diversification benefits, generally in exchange for less liquidity.
As a result, some of the world’s most sophisticated institutional investors allocate a significant portion of their
assets to these investments. Demand from individual investors for alternative investments continues to grow, but
adoption lags that of public and traditional investments. Key challenges stem from the less liquid nature of the
underlying assets, limited access and a lack of investor and advisor education.

POC

* Our goal in this project, therefore, was to identify a technology approach that could enable a wealth manager
to easily construct, deploy and automatically manage model portfolios at scale, across traditional and alternative
assets, allowing a seamless investor experience with improved transparency

> The vision for this technology layer is to enable the management of many separately managed accounts at scale,
preserving unique investor-level account customizations and reporting, while enabling the wealth manager to effect
changes to all portfolios by adjusting their reference model portfolios. With the use of blockchain technology, it
may be possible to expand the coordination and transaction of more types of assets across multiple managers on
shared ledgers to accrue even more efficiency and expand the investable universe and potential liquidity.

Wealth Management

* Can classify offerings as advisory or discretionary
* Advisory - client makes final decision
* Discretionary - manager makes final decision

Problem Statements

1. Adjusting portfolio allocations across asset types (both public and private) requires multiple systems,
manual processes and multiparty reconciliation.
2. Alternative investment funds are not commonly included in model portfolios due to operational processing
requirements and limited liquidity
    * Operational processing requirements can be high-touch, non-standard and fragmented.
    * Liquidity is limited given the nature of the underlying investments and the less-mature market infrastructure
technology.
3. Access to and distribution of tokenized assets is fragmented across blockchain networks

Tokenization Approaches

> *Asset-Backed Tokenization*: In this approach, the traditional asset continues to be recorded and custodied in
existing legacy systems like transfer agent registries, bank ledgers, or trust company or custodian accounts.
The traditional asset is immobilized in underlying ledgers and a digital pointer (in the form of a smart contract
based token) is then created on a blockchain as a representation of an investor's claim on the asset. This
establishes a digital twin of the traditional asset, similar to how depository receipts mirror securities held
in custody. The value of asset-backed tokens is the same as the underlying asset – because the blockchainbased representation is not a new asset
>
> Native Asset Tokenization: In this approach, the financial instrument itself is issued natively as a smart contract
based token on a blockchain, encapsulating the inherent contractual rights and obligations without requiring
external asset backing. Bonds, equities, fund shares and many other financial instruments can be represented
as native tokens. They are valued in the same way as 'non-blockchain' asset types but the books and records
of ownership and transfer exist only on the blockchain without necessarily depending on legacy tech systems.

Theses

* "Creating new, connected infrastructure can streamline, simplify and normalize the investment and
operational processes related to building and managing portfolios"
* "The tokenization of alts can reshape the alternative investment landscape, making it more inclusive,
transparent and efficient"


Benefits

* Greater efficiency - collapse PM and operations, enable automated rebalancing, reduce reconciliation, reduce errors
* Potentially improve liquidity - share information across multiple parties on the same ledger, simplified operations via smart contracts, automated netting of subscriptions and redemptions, etc
* Enhanced investment outcomes through alternative investments - allow alts to be included in model portfolios
* Combining the efficiency of robo-advisory with the alpha of active management
* Flexibility and broader access

Tech Components

* Base chain & smart contract platform
* Interopability capabilities
* Account abstraction/smart-contract wallets
* Smart contract event listeners
* ODA-FACT smart contracts


ODA-FACT: Onyx Digital Assets - Financial Asset Contract Templates

*Managed token* Allows permissioned deactivation and reactivation of Tokens. Needed for sanctions or admin actions — for
instance, token administrators may wish to temporarily cease all activity with tokens that represent sanctioned
assets.

*Managed account* Allows permissioned locking of balances and disallowing of accounts. Important for security measures — for
instance, token administrators may wish to prevent certain entities from further transacting with their tokens.
Allows permissioned locking of balances and force transfer/burn of locked balances within a specific account.
Useful for Account Administrators in the event of a default scenario.

*Mintable* Requires authorized mint requests before increasing token supply to an account, also reducing reconciliation.
Provides an audit trail of all requests and corresponding actions (acceptance or rejection) for a Tokenization
Agent and a requesting party.

*Burnable* Requires authorized burn requests before decreasing token supply from an account. Provides an audit trail of all
requests and corresponding actions (acceptance or rejection) for a Tokenization Agent and a requesting party.

*Token metadata* Allows setting metadata like a URI to link off-chain instrument data.


Biconomy - Smart Accounts Platform