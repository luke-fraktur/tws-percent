# Release security reference

This document records the public verification data for TWS PERCENT releases. It intentionally does **not** contain the private signing key, keystore password or any secret material.

## Official repository

- Repository: https://github.com/luke-fraktur/tws-percent
- Official releases: https://github.com/luke-fraktur/tws-percent/releases

## Current signed test release

- Tag: `v1.2.3-release`
- Release page: https://github.com/luke-fraktur/tws-percent/releases/tag/v1.2.3-release
- APK: `tws-percent-1.2.3-release.apk`
- Direct download: https://github.com/luke-fraktur/tws-percent/releases/download/v1.2.3-release/tws-percent-1.2.3-release.apk
- APK SHA-256:

```text
7954278e7cb4c094d4d97710f29a0d163f0f61d04686d1ad1f0dc447cfe54a9a
```

- Signing certificate SHA-256 fingerprint:

```text
BB:1D:77:9E:5E:BC:EC:B2:43:1F:DA:7A:66:1E:83:C3:F0:D9:FC:A4:79:A1:7C:0F:76:DB:91:59:14:B3:58:69
```

- Signature scheme verified locally: APK Signature Scheme v2.

## Previous debug test release

- Tag: `v1.2.3-test`
- Release page: https://github.com/luke-fraktur/tws-percent/releases/tag/v1.2.3-test
- This build is for development testing and is signed with a debug key. It should not be used as the production distribution artifact.

## Signing policy

- The private release keystore is kept outside the repository.
- Keystore passwords are kept outside the repository.
- The `.gitignore` blocks keystores, APKs, AABs and local signing files.
- Future release updates must be signed with the same release key or migrated through an official Play App Signing flow.
- A modified APK cannot be installed as an update over the official APK unless it has the official signing key.
- Users should download APKs only from the official GitHub Releases page.

## Verification commands

```bash
sha256sum tws-percent-1.2.3-release.apk
$ANDROID_HOME/build-tools/<version>/apksigner verify --verbose tws-percent-1.2.3-release.apk
```

The hash must match the value above, and the certificate fingerprint should match the published fingerprint.
