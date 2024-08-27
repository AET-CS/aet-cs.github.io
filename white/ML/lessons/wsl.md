# Fixing WSL

If you tried my first instructions and ran into some kind of python packaging error (looks like mysql is a culprit), here's a patch

First, you should switch to your `ml` folder in WSL and delete the old environment.

```shell
$ cd ml # or whatever
$ deactivate # may not do anything if you didn't activate the env
$ rm -rf env # this deletes the old environment
$ asdf local python 3.11.9 # make sure you're on the right python
$ python -m venv env # make the virtual environment
$ source env/bin/activate # enable the new environment
$ pip install jupyterlab numpy scikit-learn matplotlib pandas
$ jupyter-lab # make sure it works
```
When I tried this on one student machine it worked. Jupyterlab runs in a browser and you may have to click on a link that appears in your console to get it to run. (the link will contain `localhost` or `127.0.0.1`)

The problem seems to be an incompatibility with the packages I defined in `requirements.txt`. The Python Package Manager (`pip`) tries to resolve dependencies in a consistent manner but does not always succed (i.e. it usually fails on big projects.) I was hopeful the environment I carefully curated would work on windows AET machines but it doesn't. This short `pip install` command installs what we need right now and will be fine until I can properly debug with my own machine.
