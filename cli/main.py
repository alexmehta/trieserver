import click
import requests


@click.group()
def main():
    pass


@main.command()
@click.argument('word')
@click.argument('baseurl')
def delete(word, baseurl):
    response = requests.delete(baseurl + "/api/delete/" + word)
    click.echo(response.content)


@main.command()
@click.argument('baseurl')
def print(baseurl):
    response = requests.get(baseurl + "/state")
    click.echo(response.content)


@main.command()
@click.argument('word')
@click.argument('baseurl')
def insert(word, baseurl):
    response = requests.post(baseurl + "/insert/" + word)
    click.echo(response.content)


@main.command()
@click.argument('word')
@click.argument('baseurl')
def find(word, baseurl):
    response = requests.get(baseurl + "/find/" + word)
    click.echo(response.content)


@main.command()
@click.argument('word')
@click.argument('baseurl')
def predict(word, baseurl):
    response = requests.get(baseurl + "/predict/" + word)
    click.echo(response.content)


if __name__ == "__main__":
    main()
