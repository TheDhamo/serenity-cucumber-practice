# Serenity & Cucumber Practice Project

## #CodeConfident
This repository is the first chosen challenge on [my journey of becoming #CodeConfident](https://www.lisihocke.com/2018/12/a-new-pact-my-challenge-for-2019.html).

## Scope
The scope for this first practice project is to implement 3 scenarios for the test automation practice page http://automationpractice.com using Serenity and Cucumber and applying the Page Object pattern. The focus is on practicing and improving implementation skills, not covering the most risky scenarios.

## How to run the tests

### Get the code

Git:

    git clone https://github.com/lisihocke/serenity-cucumber-practice.git
    cd serenity-cucumber-practice

Or simply [download a zip](https://github.com/lisihocke/serenity-cucumber-practice/archive/master.zip) file.

### Start up a local Selenium Grid
* Start up a local Selenium Grid with one node.


    docker run -d -p 4444:4444 -P --name selenium-hub selenium/hub
    docker run -d -e no_proxy=localhost -e HUB_ENV_no_proxy=localhost -e SCREEN_WIDTH=1920 -e SCREEN_HEIGHT=1080 -p 37770:5900 -P --link selenium-hub:hub -v /dev/shm:/dev/shm selenium/node-chrome-debug

* To view the running test, start a VNC tool and go to the defined address `localhost:37770`. The password is `secret`.

### Run the tests with Gradle

Open a command window and run:

    gradlew test aggregate

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(CucumberWithSerenity.class)` annotation on the `CucumberTestSuite`
class tells JUnit to kick off Cucumber.

### Viewing the reports

The command provided above will produce a Serenity test report in the `target/site/serenity` directory. Go take a look by opening the `index.html`!

## Overriding options

The Cucumber runtime parses command line options to know what features to run, where the glue code lives, what plugins to use etc.
When you use the JUnit runner, these options are generated from the `@CucumberOptions` annotation on your test.

Sometimes it can be useful to override these options without changing or recompiling the JUnit class. This can be done with the
`cucumber.options` system property. The general form is:

Using Gradle:

    gradlew -Dcucumber.options="..." test

Let's look at some things you can do with `cucumber.options`. Try this:

    -Dcucumber.options="--help"

That should list all the available options.

### Run a subset of Features or Scenarios

Specify a particular scenario by *line* (and use the pretty plugin, which prints the scenario back)

    -Dcucumber.options="classpath:skeleton/belly.feature:4 --plugin pretty"

You can also specify files to run by filesystem path:

    -Dcucumber.options="src/test/resources/skeleton/belly.feature:4 --plugin pretty"

You can also specify what to run by *tag*:

    -Dcucumber.options="--tags @bar --plugin pretty"

### Running only the scenarios that failed in the previous run

    -Dcucumber.options="@target/rerun.txt"

This works as long as you have the `rerun` formatter enabled.

### Specify a different formatter:

For example a JUnit formatter:

    -Dcucumber.options="--plugin junit:target/cucumber-junit-report.xml"