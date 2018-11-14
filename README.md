# Important Notice
This is a fork from original project https://github.com/Red-Folder/bgs-core

This fork add:

* A cleanup method when the service is stopped.
* MyService class example.


# Background Service Plugin Core (bgs-core)

## Quick summary
This repository provides the core functionality for the Background Service Plugin for Cordova.  The functionality is not a complete plugin - it is intended to be extended by developers to create their own background service.


## Getting started

* Install this plugin
```
cordova plugin add https://github.com/icecoolinux/bgs-core.git
```

To understand how to create your own background service it is advised that you read the following articles:

* [Using the MyService Sample] (https://github.com/Red-Folder/bgs-core/wiki/Using-the-MyService-Sample)
* [Build your own Background Service] (https://github.com/Red-Folder/bgs-core/wiki/Build-your-own-Background-Service)
* [Build your own plugin] (https://github.com/Red-Folder/bgs-core/wiki/Build-your-own-plugin)

Further documentation can be found at https://github.com/Red-Folder/bgs-core/wiki

## Ionic

### Simple example

* Create an ionic project, install this plugin and add platform Android
```
ionic start myapp
cd myapp
ionic cordova plugin add https://github.com/icecoolinux/bgs-core.git
ionic cordova platform add android
```

* Start the service from index.html, add:
```
  <script>
   var myService;

   document.addEventListener('deviceready', function() {
      var serviceName = 'com.red_folder.phonegap.plugin.backgroundservice.MyService';
      var factory = cordova.require('com.red_folder.phonegap.plugin.backgroundservice.BackgroundService');
      myService = factory.create(serviceName);

      myService.startService(function(r){getStatus()}, function(e){displayError(e)});
   }, true);

   function getStatus() {
      myService.getStatus(function(r){displayResult(r)}, function(e){displayError(e)});
   }

   function displayResult(data) {
      alert("Is service running: " + data.ServiceRunning);
   }

   function displayError(data) {
      alert("We have an error");
   }
</script>
```

* Build project for Android
```
ionic cordova build android
```


### Adapted plugin for ionic

https://github.com/felipe-ff/ionic-bgs-core (Thanks felipe-ff)

* Add the plugin to your ionic project
```
ionic cordova plugin add https://github.com/felipe-ff/ionic-bgs-core
```
* Change the project id "io.ionic.starter" by your id.


## Licence
Copyright 2013 Red Folder Consultancy Ltd
    
Licensed under the Apache License, Version 2.0 (the "License");   
you may not use this file except in compliance with the License.   
You may obtain a copy of the License at       
  
http://www.apache.org/licenses/LICENSE-2.0   
 
Unless required by applicable law or agreed to in writing, software   
distributed under the License is distributed on an "AS IS" BASIS,   
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   
See the License for the specific language governing permissions and   
limitations under the License.
