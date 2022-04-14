const web3 = require("@solana/web3.js");
const PublicKey = web3.PublicKey;
const borsh = require('borsh');


(async () => {
    console.log('do it');

    //Connect
    const connection = new web3.Connection("http://localhost:8899", "confirmed");
    const version = await connection.getVersion();
    console.log(version);

    //Check deployment
    const programAddress = '5Za1N46ywxoeXKSgA6FxJc4GTsqwWKsmjgrEw4fJJk4z';

    const programId = new PublicKey(programAddress);
    const programInfo = await connection.getAccountInfo(programId);
    console.log(programInfo);

    //Generate a keypair
    const payer = web3.Keypair.generate();
    console.log(payer.publicKey.toString());

    //Fund it
    const hash = await connection.requestAirdrop(payer.publicKey, web3.LAMPORTS_PER_SOL);
    await connection.confirmTransaction(hash);

    const GREETING_SEED = 'hello';

    // Are there any methods from PublicKey to derive a public key from a seed?
    const greetedPubkey = await PublicKey.createWithSeed(
        payer.publicKey,
        GREETING_SEED,
        programId,
    );

    class GreetingAccount {
        counter = 0;
        constructor(fields) {
            if (fields) {
                this.counter = fields.counter;
            }
        }
    }

    // Borsh schema definition for greeting accounts
    const GreetingSchema = new Map([
        [GreetingAccount, { kind: 'struct', fields: [['counter', 'u32']] }],
    ]);

    // The expected size of each greeting account.
    const GREETING_SIZE = borsh.serialize(
        GreetingSchema,
        new GreetingAccount(),
    ).length;

    // This function calculates the fees we have to pay to keep the newly
    // created account alive on the blockchain. We're naming it lamports because
    // that is the denomination of the amount being returned by the function.
    const lamports = await connection.getMinimumBalanceForRentExemption(
        GREETING_SIZE,
    );

    const transaction = new web3.Transaction().add(
        web3.SystemProgram.createAccountWithSeed({
            fromPubkey: payer.publicKey,
            basePubkey: payer.publicKey,
            seed: GREETING_SEED,
            newAccountPubkey: greetedPubkey,
            lamports,
            space: GREETING_SIZE,
            programId,
        }),
    );

    const hash2 = await web3.sendAndConfirmTransaction(connection, transaction, [payer]);

    console.log(hash2);
    console.log(`gree publish key: ${greetedPubkey.toBase58()}`);

    //Get the current value
    let accountInfo = await connection.getAccountInfo(greetedPubkey);
    // Find the expected parameters.
    let greeting = borsh.deserialize(
        GreetingSchema,
        GreetingAccount,
        accountInfo.data,
    );
    console.log(greeting);

    //============== set greeting

    const instruction = new web3.TransactionInstruction({
        keys: [{ pubkey: greetedPubkey, isSigner: false, isWritable: true }],
        programId: programId,
        data: Buffer.alloc(0), // All instructions are hellos
    });

    await web3.sendAndConfirmTransaction(
        connection,
        new web3.Transaction().add(instruction),
        [payer],
    );

    //Get latest
    accountInfo = await connection.getAccountInfo(greetedPubkey);
    // Find the expected parameters.
    greeting = borsh.deserialize(
        GreetingSchema,
        GreetingAccount,
        accountInfo.data,
    );
    console.log(greeting);



})();