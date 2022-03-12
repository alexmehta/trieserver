import click
import requests
baseurl = "http://localhost:8019" + "/"
@click.group()
def main():
    pass

@main.command()
@click.argument('word')
def insert(word):
    response = requests.get(baseurl+"insert/" + word)
    click.echo(response.json())

@main.command()
@click.argument('word')
def find(word):
    response = requests.get(baseurl+"find/" +word)
    click.echo(response)
if __name__ == "__main__":
    main()
