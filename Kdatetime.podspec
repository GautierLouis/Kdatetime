Pod::Spec.new do |spec|
    spec.name                     = 'Kdatetime'
    spec.version                  = '1.0-SNAPSHOT'
    spec.homepage                 = 'Link to the Shared Module homepage'
    spec.source                   = { :git => "Not Published", :tag => "Cocoapods/#{spec.name}/#{spec.version}" }
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'A DateTimeFormatter written for KMP'

    spec.vendored_frameworks      = "build/cocoapods/framework/Kdatetime.framework"
    spec.libraries                = "c++"
    spec.module_name              = "#{spec.name}_umbrella"

    spec.ios.deployment_target = '15.0'
    spec.osx.deployment_target = '11.0'
    spec.tvos.deployment_target = '15.2'
    spec.watchos.deployment_target = '7.2'

                

    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':',
        'PRODUCT_MODULE_NAME' => 'Kdatetime',
    }

    spec.script_phases = [
        {
            :name => 'Build Kdatetime',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$COCOAPODS_SKIP_KOTLIN_BUILD" ]; then
                  echo "Skipping Gradle build task invocation due to COCOAPODS_SKIP_KOTLIN_BUILD environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../../../../private/var/folders/3b/sbjy_rxn4gg_htfcbpcq7n2c0000gn/T/wrap3loc/gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration=$CONFIGURATION
            SCRIPT
        }
    ]
end