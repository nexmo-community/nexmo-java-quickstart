# Nexmo Quickstart Examples for Java

<img src="https://developer.nexmo.com/assets/images/Vonage_Nexmo.svg" height="48px" alt="Nexmo is now known as Vonage" />

Quickstarts also available for: [Python](https://github.com/Nexmo/nexmo-python-code-snippets), [.NET](https://github.com/Nexmo/nexmo-dotnet-code-snippets), [Node.js](https://github.com/Nexmo/nexmo-node-code-snippets), [PHP](https://github.com/Nexmo/nexmo-php-code-snippets),  [Ruby](https://github.com/Nexmo/nexmo-ruby-code-snippets) and [curl](https://github.com/Nexmo/nexmo-curl-code-snippets).

The purpose of the quickstart guide is to provide simple examples focused on one goal. For example, sending an SMS, handling an incoming SMS webhook, making a Text to Speech call. These code samples are meant to be used for https://developer.nexmo.com/, and are structured in such a way as to be used for internal testing. Developers are free to use these code snippets as a reference, but these may require changes to be worked into your specific application. We recommend checking out the Nexmo Developer Website, which displays these code snippets in a more copy/paste fashion.

## Setup

To use this sample you will first need a [Nexmo account][sign-up].

For some of the examples you will need to [buy a number][buy-number].

## Building The Library

You will need to have [Gradle](https://gradle.org/) installed to build the code. Once
you have gradle installed, run the following to build a jar that contains
the quickstart code along with all the nexmo client library dependencies:

```sh
gradle assemble
```

This will build the following file: `build/libs/nexmo-java-quickstart-with-dependencies.jar`

## Running The Examples

Copy `.env-example` to `.env` and edit the values. You'll need to load those
values into environment variables, so you'll probably want to use a tool like
[Foreman](https://github.com/ddollar/foreman) to run your code like this:

```sh
foreman run java -cp build/libs/nexmo-java-quickstart-with-dependencies.jar CLASS
```

So to run the OutboundTextToSpeechExample class, you would run the following:

```sh
foreman run java -cp build/libs/nexmo-java-code-snippets-with-dependencies.jar com.nexmo.quickstart.voice.OutboundTextToSpeech
```

If you set the environment variable `QUICKSTART_DEBUG` to any value, extra information
will be output to the console from the Nexmo Client library.

## Request an Example

Please [raise an issue](https://github.com/nexmo-community/nexmo-java-quickstart/issues) to request an example that isn't present within the quickstart. Pull requests will be gratefully received.

## License

This code is licensed under the [MIT](LICENSE.txt) license.

[gradle]: https://gradle.org/
[foreman]: https://github.com/ddollar/foreman
[sign-up]: https://dashboard.nexmo.com/sign-up
[buy-number]: https://dashboard.nexmo.com/buy-numbers
