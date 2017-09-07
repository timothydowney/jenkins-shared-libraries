def call(config=[:],
         Closure body) {

    // NPM_CONF_USERCONFIG would be nice, but it doesn't seem to work
    def variable = config.variable
    def targetLocation = config.targetLocation ?: '.npmrc'
    def targetDir = config.targetDir ?: '.'
    def replaceTokens = config.replaceTokens ?: true

    def npmrcFile = "${targetDir}/${targetLocation}"

    if (!config.npmrcId) {
        error "'npmrcId' is required"
    }

    if (config.image) {
        withDockerContainer(image: config.image, args: config.dockerArgs) {
            // Grab the proper npmrc
            configFileProvider([configFile(fileId: config.npmrcId, targetLocation: npmrcFile, variable: variable, replaceTokens: replaceTokens)]) {
                sh "cat ${npmrcFile}"
                sh "cd ${targetDir} && npm config list"

                // run the body
                body()
            }
        }
    } else {
        // Grab the proper npmrc
        configFileProvider([configFile(fileId: config.npmrcId, targetLocation: npmrcFile, variable: variable, replaceTokens: replaceTokens)]) {
            sh "cat ${npmrcFile}"
            sh "cd ${targetDir} && npm config list"

            // run the body
            body()
        }
    }
}
