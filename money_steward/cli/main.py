import click

from .commands import config, users

@click.group()
def main():
  "Command line tool to interact with money-steward"
  click.echo("Hello World!")

main.add_command(config.config)
main.add_command(users.users)
