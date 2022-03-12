# Trie Server and CLI

## Features

- Add keyword to trie
- Delete a keyword from trie
- Search for a keyword in trie [True/False]
- Return list of autocomplete suggestion based on an input prefix
- Display the trie

## Usage/Examples

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

