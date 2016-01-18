# Guide to contributing

Please read this if you intend to contribute to the project.

## Legal stuff

Apologies in advance for the extra work required here - this is necessary to comply with the Eclipse Foundation's
strict IP policy.

Please also read [Contributing via Git](http://wiki.eclipse.org/Development_Resources/Contributing_via_Git) (Eclipse Wiki).

In order for any contributions to be accepted you MUST do the following things.

* Sign the [Eclipse Foundation Contributor License Agreement](http://www.eclipse.org/legal/CLA.php).
To sign the Eclipse CLA you need to:

  * Obtain an Eclipse Foundation userid. Anyone who currently uses Eclipse Bugzilla or Gerrit systems already has one of those.
If you don’t, you need to [register](https://dev.eclipse.org/site_login/createaccount.php).

  * Login into the [projects portal](https://projects.eclipse.org/), select “My Account”, and then the “Contributor License Agreement” tab.

* Add your github username in your Eclipse Foundation account settings. Log in it to Eclipse and go to account settings.

* "Sign-off" your commits (NOTE! This is only necessary if contributing to the vertx-core project)

Every commit you make in your patch or pull request MUST be "signed off".

You do this by adding the `-s` flag when you make the commit(s), e.g.

    git commit -s -m "Shave the yak some more"

## Making your changes

### Fork the repository on GitHub

Use Eclipse to check out the repo.

1. Install [Eclipse for RCP and RAP Developers](http://www.eclipse.org/downloads/).  Other versions may work.  These instructions were tested using Mars.1 January 2016 under Mac OS X.
2. Start Eclipse. Create a new workspace.  You may need to close the Welcome tab by clicking on the X.
3. Window -> Show View -> Other -> Git -> Git Repositories
4. Click on "Clone a Git repository"
5. Click on "Clone URI", then Next
6. Enter the URI ``https://github.com/eclipse/triquetrum/``
7. In the Branch Selection window, keep the default of the Master branch (FIXME: Should the other branches be deselected?) and click Next.
8. In the Local Destination window, select Finish.

### Create a new branch for your changes

1. In the Git Repositories tab, expand the triquetrum repository.
2. Right click on the "Branches" node and select "Switch To" -> "New Branch".  Enter the new branch name, which is typically your login, followed by a dash and then a very short description.  (FIXME: What about "Configure upstream for push and pull" in this dialog? See [Branch Creation Dialog in the EGit User Guide](https://wiki.eclipse.org/EGit/User_Guide#Branch_Creation_Dialog) and http://www.btday.com/egit-configure-upstream-for-push-and-pull/)

### Committing
* Make your changes
* Make sure you include tests
* Make sure the test suite passes after your changes
* Commit your changes into that branch
* Use descriptive and meaningful commit messages. See [git commit records in the Eclipse Project Handbook](https://www.eclipse.org/projects/handbook/#resources-source)

* If you have a lot of commits squash them into a single commit
* Make sure you use the `-s` flag when committing as explained above.
* Push your changes to your branch in your forked repository

## Submitting the changes

Submit a pull request via the normal GitHub UI.
 
## After submitting

* Do not use your branch for any other development, otherwise further changes that you make will be visible in the PR.

# Credit

This file is based on a file written by the Vert.x team at https://raw.githubusercontent.com/eclipse/vert.x/master/CONTRIBUTING.md

We have shamelessly copied, modified and co-opted it for our own repo and we graciously acknowledge the work of the original authors.
