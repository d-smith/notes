# CCTP Notes

Circle - https://www.circle.com/en/cross-chain-transfer-protocol

Developer docs - https://developers.circle.com/stablecoins/docs/cctp-getting-started

Bridges - https://ethereum.org/en/developers/docs/bridges/#how-do-bridges-work

* lock and mint -  Lock assets on the source chain and mint assets on the destination chain.

Contracts

* TokenMessenger: Entrypoint for cross-chain USDC transfer. Routes messages to burn USDC on a source chain, and mint USDC on a destination chain.
* MessageTransmitter: Generic message passing. Sends all messages on the source chain, and receives all messages on the destination chain.
* TokenMinter: Responsible for minting and burning USDC. Contains chain-specific settings used by burners and minters.

(Descriptions taken from circle docs)


Roles

* Attestable - an ownable 2 step use to manage attestation config, plus some logic to check if a message is attested
* Ownable - Forked from open zeppelin
* Ownable2Step - forked from open zeppelin, allows transfer of ownership post deployment
* Pausable
* Rescuable
* TokenController - not really a role, but an abstract contract used to link tokens across domains

Open Zeppelin Acess Control - see https://docs.openzeppelin.com/contracts/2.x/access-control

* Ownable - most basic form of access control: a contract has an owner can can do administrative stuff on it
* Ownable2Step - transfer of ownership requires acceptance by the new owner

## MessageTransmitter

![MessageTransmitter](./MessageTransmitter.png)

## TokenMessenger

![TokenMessenger](./tokenMessenger.png)

## TokenMinter

![TokenMinter](./tokenMinter.png)


## Deployment

1. Deploy the MessageTransmitter contract
1.1 Add pauser
1.2 Add rescuer

Note the attestable address must be known at deployment time

2. Deploy TokenMessenger contract
2.1 Uses address from deployment of MessageTransmitter
2.2 Add rescuer

3. Deploy TokenMinter contract
3.1 Uses address from deployment of TokenMessenger
3.2 Add local token messenger 
3.3 Update pauser
3.4 Update rescuer

4. Add minter address to token messenger

5. Link token pair

* This links the token pair contract address with the remote address and remote domain
* In the deployment script the max burn amount per message is also set

6. Add remote token messenger to token minter


Note contract addresses can be [precomputed](https://ethereum.stackexchange.com/questions/760/how-is-the-address-of-an-ethereum-contract-computed).


![Deployment](./deployed.png)
