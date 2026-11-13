## Telegram messenger for Android

[Telegram](https://telegram.org) is a messaging app with a focus on speed and security. It’s superfast, simple and free.
This repo contains the official source code for [Telegram App for Android](https://play.google.com/store/apps/details?id=org.telegram.messenger).

## Creating your Telegram Application

We welcome all developers to use our API and source code to create applications on our platform.
There are several things we require from **all developers** for the moment.

1. [**Obtain your own api_id**](https://core.telegram.org/api/obtaining_api_id) for your application.
2. Please **do not** use the name Telegram for your app — or make sure your users understand that it is unofficial.
3. Kindly **do not** use our standard logo (white paper plane in a blue circle) as your app's logo.
3. Please study our [**security guidelines**](https://core.telegram.org/mtproto/security_guidelines) and take good care of your users' data and privacy.
4. Please remember to publish **your** code too in order to comply with the licences.

### API, Protocol documentation

Telegram API manuals: https://core.telegram.org/api

MTproto protocol manuals: https://core.telegram.org/mtproto

### Compilation Guide

**Note**: In order to support [reproducible builds](https://core.telegram.org/reproducible-builds), this repo contains dummy release.keystore,  google-services.json and filled variables inside BuildVars.java. Before publishing your own APKs please make sure to replace all these files with your own.

You will require Android Studio 2025.1.4, Android NDK 27.2.12479018 and Android SDK 36.

1. Clone the Telegram source code with its submodules:
   ```bash
   git clone --recursive --shallow-submodules https://github.com/DrKLO/Telegram.git Telegram
   ```
   In case you forgot the `--recursive` flag, change to the `Telegram` directory and run:
   ```bash
   git submodule init && git submodule update --init --recursive --depth=1
   ```
2. Copy your release.keystore into TMessagesProj/config
3. Fill out RELEASE_KEY_PASSWORD, RELEASE_KEY_ALIAS, RELEASE_STORE_PASSWORD in gradle.properties to access your  release.keystore
4.  Go to https://console.firebase.google.com/, create two android apps with application IDs org.telegram.messenger and org.telegram.messenger.beta, turn on firebase messaging and download google-services.json, which should be copied to the same folder as TMessagesProj.
5. Open the project in the Studio (note that it should be opened, NOT imported).
6. Fill out values in TMessagesProj/src/main/java/org/telegram/messenger/BuildVars.java – there’s a link for each of the variables showing where and which data to obtain.
7. You are ready to compile Telegram.

### Localization

We moved all translations to https://translations.telegram.org/en/android/. Please use it.


## Avioverse starter work

The `avioverse-starter` branch is the working branch for the unofficial Avioverse Android prototype.

### Current offline-first milestone

- The launcher opens `AvioverseHomeActivity` directly. The first screen does not ask for a phone number, SMS code, Telegram login or Avioverse registration.
- The first prototype has three tabs: Chats, Settings and Profile.
- The profile name is stored locally on the device.
- Chats are a placeholder: there is no messaging backend and messages cannot yet be sent to other people.
- Avioverse demo Stars show an unlimited balance and Avioverse gifts (bear, rose, crystal and rocket) are local UI examples only. They are not Telegram Stars, payment instruments, transferable assets or real gifts.
- The offline home screen does not use a Telegram account. The original Telegram client code remains in the source tree for reference and must not be presented as the Avioverse messaging service.
- No independent messaging server exists yet. Real multi-user chat requires a separately designed backend and security model.

### Build

GitHub Actions builds the debug APK on pushes to `avioverse-starter`. Open the Actions tab and download the `avioverse-debug-apk` artifact after a successful run. The first build may take a long time because Telegram includes substantial native code and submodules.

### Licensing and identity

This is an unofficial Avioverse prototype based on the Telegram Android source tree. Preserve the applicable GPL-2.0 licensing and source-distribution obligations when distributing modified builds. Do not imply that Avioverse is an official Telegram app or that its demo Stars/gifts are Telegram products.
