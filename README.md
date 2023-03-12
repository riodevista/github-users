# GitHub Users

This Android app displays a list of GitHub users retrieved from a GitHub REST API using Ktor as the HTTP client and Jetpack Compose for the UI. The app uses the MVVM architecture with Hilt for dependency injection.

<table>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/9745990/224025823-5e38d101-4bc9-45e3-8adf-5218024a17a7.png" alt="main screen" width="200"/></td>
    <td><img src="https://user-images.githubusercontent.com/9745990/224026019-74b7fbc3-d90f-40e9-8b04-21d770c7d88e.png" alt="details screen" width="200"/></td>
    </td>
    <td><img src="https://user-images.githubusercontent.com/9745990/224564959-854684c5-a9be-4a67-8f89-00e0d69613e6.png" alt="pagination" width="200"/></td>
  </tr>
  <tr>
    <td>Main Screen</td>
    <td>Details Screen</td>
    <td>Pagination</td>
  </tr>
</table>


## Technologies

List of technologies used in the project:
- Hilt for dependency injection
- Ktor as the HTTP client
- Jetpack Compose for the UI
- Coil for avatar showing

## Setup

Instructions on setting up and running the project:
1. Clone the repository.
2. Open the project in Android Studio.
3. Build the project.
4. Run the app on an emulator or physical device.

## Usage

Instructions on how to use the app:
1. Launch the app.
2. The app displays a list of GitHub users.
3. Tap an user to view its details.

## Contributing


Instructions on how to contribute to the project and guidelines for submitting pull requests:

1. Fork the project.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Create a new Pull Request.


## Versions

### Version 1.1
- Added pagination to improve performance and user experience when loading a large number of users

### Version 1.0
- Initial release of GitHub Users, with basic functionality to display a list of users and navigate to their details screen


## License

This project is not licensed under the MIT License.
