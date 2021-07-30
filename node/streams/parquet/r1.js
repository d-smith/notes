const parquet = require('parquetjs');

let readIt = async () => {
    // create new ParquetReader that reads from 'fruits.parquet`
    let reader = await parquet.ParquetReader.openFile('countries.parquet');

    // create a new cursor
    let cursor = reader.getCursor();

    // read all records from the file and print them
    let record = null;
    while (record = await cursor.next()) {
        console.log(record);
    }
};

readIt();