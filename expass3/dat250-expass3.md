# DAT250 Experiment Assignment 3

## MongoDB installation verification

As mongodb is installed on my ubuntu system via `apt` verification is not really a problem, however to please the grading Gods I used [debsums](https://manpages.ubuntu.com/manpages/trusty/man1/debsums.1.html) to verify the `md5` sums of the mongodb packages.

![Verification of the mongo db installation](screenshots/mongodb-verification.png)

## Experiment 1

![All four CRUD operations](screenshots/mongodb-crud.png)

## Experiment 2

![Example map reduce function 1](screenshots/experiment2-example-map-reduce-func1.png)
![Example map reduce function 2](screenshots/experiment2-example-map-reduce-func2.png)

### Custom map reduce function

Source for this function can be found in [custom-mongodb-map-reduce.js](custom-mongodb-map-reduce.js)

![Custom map reduce function](screenshots/experiment2-custom-map-reduce-func.png)

## Usefulness of custom function

The custom function tells us when the last order was made, so we might remind them that we exist so they order from us even more.

## Issues

There are no pending issues with this assignment that has not been resolved.
