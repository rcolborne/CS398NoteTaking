@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  noteTakingApp startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and NOTE_TAKING_APP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\noteTakingApp.jar;%APP_HOME%\lib\kotlin-stdlib-jdk8-1.6.10.jar;%APP_HOME%\lib\kotlin-stdlib-jdk7-1.6.10.jar;%APP_HOME%\lib\kotlinx-serialization-json-jvm-1.3.2.jar;%APP_HOME%\lib\kotlinx-serialization-core-jvm-1.3.2.jar;%APP_HOME%\lib\kotlin-stdlib-1.6.10.jar;%APP_HOME%\lib\voice-cmu-rms-hsmm-5.2.jar;%APP_HOME%\lib\javafx-web-17.0.2-mac.jar;%APP_HOME%\lib\javafx-controls-17.0.2-mac.jar;%APP_HOME%\lib\javafx-controls-17.0.2.jar;%APP_HOME%\lib\javafx-media-17.0.2-mac.jar;%APP_HOME%\lib\javafx-media-17.0.2.jar;%APP_HOME%\lib\javafx-graphics-17.0.2-mac.jar;%APP_HOME%\lib\javafx-graphics-17.0.2.jar;%APP_HOME%\lib\javafx-base-17.0.2-mac.jar;%APP_HOME%\lib\javafx-base-17.0.2.jar;%APP_HOME%\lib\annotations-13.0.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.6.10.jar;%APP_HOME%\lib\marytts-lang-en-5.2.jar;%APP_HOME%\lib\marytts-runtime-5.2.jar;%APP_HOME%\lib\marytts-signalproc-5.2.jar;%APP_HOME%\lib\marytts-common-5.2.jar;%APP_HOME%\lib\icu4j-54.1.1.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\emotionml-checker-java-1.1.jar;%APP_HOME%\lib\jtok-core-1.9.3.jar;%APP_HOME%\lib\guava-14.0.1.jar;%APP_HOME%\lib\trove4j-2.0.2.jar;%APP_HOME%\lib\httpcore-nio-4.1.jar;%APP_HOME%\lib\httpcore-4.1.jar;%APP_HOME%\lib\opennlp-tools-1.5.3.jar;%APP_HOME%\lib\opennlp-maxent-3.0.3.jar;%APP_HOME%\lib\hsqldb-2.0.0.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\slf4j-log4j12-1.6.1.jar;%APP_HOME%\lib\log4j-1.2.16.jar;%APP_HOME%\lib\fast-md5-2.7.1.jar;%APP_HOME%\lib\groovy-all-2.4.5.jar;%APP_HOME%\lib\Jampack-1.0.jar;%APP_HOME%\lib\jama-1.0.3.jar;%APP_HOME%\lib\swing-layout-1.0.3.jar;%APP_HOME%\lib\slf4j-api-1.6.1.jar;%APP_HOME%\lib\jwnl-1.3.3.jar


@rem Execute noteTakingApp
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %NOTE_TAKING_APP_OPTS%  -classpath "%CLASSPATH%" NoteTakingApp %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable NOTE_TAKING_APP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%NOTE_TAKING_APP_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
