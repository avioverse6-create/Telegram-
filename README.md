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

The `avioverse-starter` branch is the working branch for the unofficial Android client customization.

Current first milestone:
- Keep the Telegram Android codebase as the client foundation.
- Plan a three-item bottom navigation: Chats, Profile, Settings.
- Keep gifts and Stars clearly in demo mode until a legitimate payment/account integration exists. A local demo balance is not real Telegram Stars and cannot be spent or transferred.
- No independent messaging backend exists yet. Until one is designed and deployed, this source remains a Telegram-network client foundation; it is not a standalone messaging network.

### Build

Use GitHub Actions → Android debug APK → Run workflow, or push a commit to `avioverse-starter`. The workflow checks out submodules and attempts the `afatDebug` APK build. The first build may take a long time because Telegram includes substantial native code and submodules.

Before distributing a build, configure your own Telegram API credentials, app identity, signing key and any required Firebase configuration. Do not distribute the repository's sample credentials or signing setup.


### Existing Telegram Stars and gifts

The source tree already contains Telegram's native Stars and gift flows (including the Stars intro/balance UI and the Send a Gift entry in Settings). These remain connected to Telegram's account and server-side eligibility/purchase rules. Avioverse will not fabricate spendable Stars or claim that a local unlimited counter is real currency. A separate unlimited demo counter can be added later, clearly marked as non-transferable and non-purchasable.

### First UI milestone

- Main navigation is reduced to Chats, Settings and Profile.
- The Settings and Profile destinations reuse Telegram's existing functional screens and account data.
- The Settings screen already contains native Telegram Stars and Send a Gift entries where Telegram makes them available.
