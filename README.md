# Oxygen
This Android sdk containes all the models, services and widgets required to build an App.
Add the dependency and make use of all the services and models related to Quintype CMS

To get a Git project into your build:

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
### Step 2. Add the dependency:

    dependencies {
	        implementation 'com.github.quintype:android-oxygen:Tag'
	  }	
  
### Step 3. Initialize Oxygen library from your Apps application class.
	
    OxygenConstants.initBaseUrl(BASE_URL)
  BASE_URL : Based on publisher.
