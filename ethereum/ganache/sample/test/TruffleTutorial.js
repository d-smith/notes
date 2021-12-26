const { assert } = require("chai")

const TruffleTutorial = artifacts.require("./TruffleTutorial.sol")

require("chai")
  .use(require("chai-as-promised"))
  .should()

contract('TruffleTutorial', ([contractOwner, secondAddress, thirdAddress]) => {
  let truffleTutorial

  // this would attach the deployed smart contract and its methods 
  // to the `truffleTutorial` variable before all other tests are run
  before(async () => {
    truffleTutorial = await TruffleTutorial.deployed()
  })

  // check if deployment goes smooth
  describe('deployment', () => {
    // check if the smart contract is deployed 
    // by checking the address of the smart contract
    it('deploys successfully', async () => {
      const address = await truffleTutorial.address

      assert.notEqual(address, '')
      assert.notEqual(address, undefined)
      assert.notEqual(address, null)
      assert.notEqual(address, 0x0)
    })

    // check if the message is stored on deployment as expected
    it('has a message', async () => {
      const message = await truffleTutorial.message()
      assert.equal(message, 'Hello World!')
    })
  })

  describe('message', () => {
    // check if owner can set new message, check if setMessage works
    it('contract owner sets a message', async () => {
      // set new message
      await truffleTutorial.setMessage('Hi there!', { from: contractOwner }) 
      // `from` helps us identify by any address in the test

      // check new message
      const message = await truffleTutorial.message()
      assert.equal(message, 'Hi there!')
    })

    // make sure only owner can setMessage and no one else
    it('address that is not the owner fails to set a message', async () => {
      await truffleTutorial.setMessage('Hi there!', { from: secondAddress })
        .should.be.rejected
      // this tells Chai that the test should pass if the setMessage function fails.

      await truffleTutorial.setMessage('Hi there!', { from: thirdAddress })
        .should.be.rejected
    })
  })
})