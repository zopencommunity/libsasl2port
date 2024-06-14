node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/libsasl2port.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/libsasl2port.git'), string(name: 'PORT_DESCRIPTION', value: 'his is the Cyrus SASL API implementation. It can be used on the client or server side to provide authentication and authorization services. See RFC 4422 for more information.' ), string(name: 'BUILD_LINE', value: 'DEV') ]
  }
}
