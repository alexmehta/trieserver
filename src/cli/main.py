import click
import requests
import os

baseurl = "http://localhost:8821" + "/"
@click.group()
def main():
    pass

@main.command()
@click.argument('word')
def insert(word):
    response = requests.post(baseurl+"insert/" + word)
    click.echo(response.content)

@main.command()
@click.argument('word')
def find(word):
    response = requests.get(baseurl+"find/" +word)
    click.echo(response.content)

@main.command()
@click.argument('word')
def predict(word):
    response = requests.get(baseurl+"predict/" + word)
    click.echo(response.content)

if __name__ == "__main__":
    main()
