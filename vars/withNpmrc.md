# NPM Configuration
Using the specified npmrc, perform an NPM based build.

The provided npmrc will be copied to the workspace root.  If a `targetDir` is specified, the provided npmrc
will be copied there.  This allows build specific overrides based on configuration in the npmrc.

*Please note that the notation is a bit different than usual due to the ability to pass a closure to the body*

# Parameters

| Parameter |          Type    | Required | Default  | Description |
|-----------|          ----    | -------- | -------- | ----------- |
| `npmrcId`           | string | `yes`    |          | Id of the npmrc managed file previously stored on Jenkins |
| `targetDir`         | string | `no `    |    `.`   | Target directory where the npmrc will be placed |
| `image`             | string | `no`     |          | Optional Docker image that will be used to run the body |
| `dockerArgs`        | string | `no`     |          | Optional arguments that will be used when running the container |
| `body`              | closure| `yes`    |          | A closure to be run with the npmrc |

# Examples

```groovy
withNpmrc([npmrcId: 'my-npmrc']) {
    sh 'npm install'
    sh 'grunt build'
}

withNpmrc([npmrcId: 'my-npmrc', image: 'dtr.cdl.es.ad.adp.com/nasdevops/node-grunt-phantomjs:6.10.3']) {
    sh 'npm install'
    sh 'grunt build'
}

```