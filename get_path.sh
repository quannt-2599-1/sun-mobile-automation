#!/bin/bash

# Check if the module name is provided
nodejs_path=$(which node)
if [ -n "$nodejs_path" ]; then
  echo "[Node.js path] $nodejs_path"
else
  echo "Node.js is not installed."
fi

java_home=$(echo $JAVA_HOME)
if [ -n "$java_home" ]; then
  echo "[JAVA_HOME] $java_home"
else
  echo "JAVA_HOME is not set."
fi

android_home=$(echo $ANDROID_HOME)
if [ -n "$android_home" ]; then
  echo "[ANDROID_HOME] $android_home"
else
  echo "ANDROID_HOME is not set."
fi

# Find the global path of the Node.js module
node_module_path=$(npm list -g appium 2>/dev/null | grep lib)

# Check if the variable is not empty
if [ -n "$node_module_path" ]; then
  echo "Appium module found."
else
  echo "Appium module not found."
  exit 1
fi

# Check if you can cd into the directory
if cd "$node_module_path/node_modules/appium" 2>/dev/null; then
  appium_path=$(pwd)
  if cd "$appium_path/build/lib" 2>/dev/null; then
    check_build_lib_js=$(ls | grep "main.js")
    if [ -n "$check_build_lib_js" ]; then
      echo "[AppiumJS path] $appium_path/build/lib/main.js"
    else
      echo "Failed to find appiumJS path in: $appium_path"
      exit 1
    fi
  else
    if cd "$appium_path/lib" 2>/dev/null; then
      check_lib_js=$(ls | grep "main.js")
      if [ -n "$check_lib_js" ]; then
        echo "[AppiumJS path] $appium_path/lib/main.js"
      else
        echo "Failed to find appium path in: $appium_path"
        exit 1
      fi
    else
      echo "Failed to find appium path in: $appium_path"
      exit 1
    fi
  fi
else
  echo "Failed to find node_modules path in: $node_module_path"
  exit 1
fi
