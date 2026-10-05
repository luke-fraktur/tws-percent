# Validation and execution evidence

This page separates **observed evidence** from **planned or theoretical scenarios**. It is intentionally conservative: a scenario is not presented as tested on a device unless a result is recorded.

## Observed evidence

| Evidence | Result | Public reference |
|---|---|---|
| Native Android project compilation | Passed locally and through GitHub Actions | [Actions workflow](https://github.com/luke-fraktur/tws-percent/actions/workflows/android.yml) |
| JVM battery classification tests | 4 tests passed, 0 failures, 0 errors locally | `BatteryStatusTest` |
| Debug APK artifact | Published | [Debug test release](https://github.com/luke-fraktur/tws-percent/releases/tag/v1.2.3-test) |
| Release APK signature verification | Passed with APK Signature Scheme v2 | [Signed release](https://github.com/luke-fraktur/tws-percent/releases/tag/v1.2.3-release) |
| Widget visual states | Demonstrated in a recorded video | [Download demo video](https://github.com/luke-fraktur/tws-percent/releases/download/v1.2.3-release/tws-percent-widget-demo.mp4) |
| App visual screen | Included in the repository | [App screenshot](media/dvorah-app-screen.jpg) |
| Widget visual preview | Included in the repository | [Widget screenshot](media/widget-preview.jpg) |

## Automated tests

The project includes JVM unit tests for the battery classification boundaries:

```bash
./gradlew testDebugUnitTest
```

The current automated coverage verifies:

- Unknown battery level (`-1`).
- Low range: `0–29`.
- Medium range: `30–69`.
- High range: `70–100`.
- Boundary values `29`, `30`, `69` and `70`.

GitHub Actions runs the unit tests before building the debug APK on pushes and pull requests to `main`:

[![Android build and tests](https://github.com/luke-fraktur/tws-percent/actions/workflows/android.yml/badge.svg)](https://github.com/luke-fraktur/tws-percent/actions/workflows/android.yml)

## Manual test checklist

These are the practical checks to perform on an Android device. The checklist is not a claim that every device model has already been tested.

- [ ] Install the signed test APK from the official Release.
- [ ] Grant Nearby devices permission on Android 12+.
- [ ] Add the TWS PERCENT widget at its default size.
- [ ] Resize the widget horizontally and vertically when the launcher allows it.
- [ ] Confirm that the percentage remains legible over the bee artwork.
- [ ] Confirm green, yellow and red states using earbuds with different charge levels.
- [ ] Tap the widget and confirm an immediate refresh attempt.
- [ ] Open the internal app screen.
- [ ] Open the Linktree button.
- [ ] Open the Youtube button.
- [ ] Reboot the device and observe whether the periodic refresh is restored.

## Theoretical compatibility scenarios

The following cases are design expectations and require device-specific validation:

- A paired earbud exposes a standard battery level through the Android Bluetooth API.
- A manufacturer exposes only the charging-case level.
- A manufacturer exposes left and right earbuds separately.
- A manufacturer exposes no battery level to third-party apps.
- Android battery optimization delays the five-minute alarm.
- A launcher applies a non-square cell ratio to a nominal 1×1 widget.
- A device denies Bluetooth permission or has Bluetooth disabled.

The app handles unavailable readings by showing `--%` in white. Manufacturer-specific GATT support remains a roadmap item.

## Reproducible evidence commands

```bash
./gradlew testDebugUnitTest assembleDebug
sha256sum app/build/outputs/apk/debug/app-debug.apk
$ANDROID_HOME/build-tools/<version>/apksigner verify --verbose app/build/outputs/apk/release/app-release.apk
```

The public release page contains the signed APK hash and certificate fingerprint needed for independent verification.
