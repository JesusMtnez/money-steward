import click

@click.command
def users():
  click.echo("running users subcommand")
