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

### Create an Issue
Create a [GitHub Issue](https://github.com/eclipse/triquetrum/issues) for every significant piece of work ( > 2 hrs).

### Create a new branch for your changes

1. In the Git Repositories tab, expand the triquetrum repository.
2. Right click on the "Branches" node and select "Switch To" -> "New Branch".  
3. Enter the new branch name, -Branch name should be {dvp-er}/{type}/{issue id}/{summary-description}
e.g. "erwin/ft/5/integrate-display-actor". Types: ft (feature i.e. with functional value); eh (enhancement without functional value); bg (bug); doc; ... ? which is typically your login, followed by a dash and then a very short description. 

 
### Committing
* Make your changes
* Make sure you include tests
* Make sure the test suite passes after your changes
* Commit your changes into that branch. 
* For files that are in Eclipse packages, right click on the file in the Package Explorer and commit it.  
* For files that are not in Eclipse packages, invoke the Git Staging via Window -> Show View -> Other -> Git -> Git
* Use descriptive and meaningful commit messages. See [git commit records in the Eclipse Project Handbook](https://www.eclipse.org/projects/handbook/#resources-source).  Mention issue_id in each commit comment using syntax like "Adapt this interface for #15" to link to issue 15.
* If you have a lot of commits squash them into a single commit (**FIXME: How do we do this? and is it really necessary?  Not squashing the commits gives finer granularity tracking changes when debugging**)
* Make sure you use the `-s` flag when committing as explained above.
* Push your changes to your branch in your forked repository

## Submitting the changes

1. Submit a pull request via the normal [GitHub UI](https://github.com/eclipse/triquetrum) to trigger to request feedback / code review / ... 
2. Mention issue_id in each comment using syntax like "Adapt this interface for #15" to link to issue 15 in the initial comment for a Pull Request.
3. Only merge your work to master after positive feedback/review. (**FIXME: Not sure about this requirement yet**)
 
## After submitting

* Do not use your branch for any other development, otherwise further changes that you make will be visible in the PR.

# Credit

This file is based on a file written by the Vert.x team at https://raw.githubusercontent.com/eclipse/vert.x/master/CONTRIBUTING.md

We have shamelessly copied, modified and co-opted it for our own repo and we graciously acknowledge the work of the original authors.
