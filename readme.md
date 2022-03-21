# Trie Server and CLI

## Features

- Add keyword to trie
- Delete a keyword from trie
- Search for a keyword in trie [True/False]
- Return list of autocomplete suggestion based on an input prefix
- Display the trie
- Example server at: **trie.alexmehta.xyz**

## Usage/Examples

Note you must install required packages for the python cli to work. This is just ```pip install click```. CLI folder is [here](https://github.com/alexmehta/trieserver/tree/main/cli) 

```
python3 cli/main.py insert word <url for host here>
python3 cli/main.py delete word <url for host here>
python3 cli/main.py find word <url for host here>
python3 cli/main.py predict word <url for host here>

```

## API Reference

Note that localhost should be replaced with the server url. In this case, there is a test server at trie.alexmehta.xyz

#### Insert word (POST request)

```http://localhost:8019/insert/{word here}```

#### Delete word

```http://localhost:8019/delete/{word here}```

#### Get prediction

```http://localhost:8019/predict/{word}```

#### Display Trie

```http://localhost:8019/state/```

#### Download api docs (json)

```http://localhost:8019/v2/api-docs```

## Run Locally

Install Java Maven

Clone this repo

Run the setup.sh script and the server will start.

Use the CLI or REST endpoints to use the Trie

## Running Tests

Tests are Junit tests in the src/test/java folder

## License

[MIT](https://choosealicense.com/licenses/mit/)

## Authors

- [@alexmehta](https://www.github.com/alexmehta)

## Additional Improvements
- Multi Node Hosting (with docker)
  - Hosting in more than one location with kubernetes to scale
  - Docker Container and Compose for setup
- Python Script to run external api tests 
  - Currently JUnit tests are located in the [tests folder](https://github.com/alexmehta/trieserver/tree/main/src/test/java) 
  - This tests endpoints internally, but an external test might be a good idea for a production level build (to ensure it works outside the network)

