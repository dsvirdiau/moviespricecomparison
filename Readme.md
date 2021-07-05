[![LinkedIn][linkedin-shield]][linkedin-url]
[![Platform][platform-android-shield]][platform-android-url]
[![Language][language-kotlin-sheild]][language-kotlin-url]


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/dsvirdiau/moviespricecomparison">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Price's Theatre Price Comparison App</h3>

  <p align="center">
    An app to compare and show price between two theatres named filmworld and cinemaworld. The lower price is highlighted for better visibility
    <br />
    <a href="https://github.com/dsvirdiau/moviespricecomparison"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/dsvirdiau/moviespricecomparison">View Demo</a>
    ·
    <a href="https://github.com/dsvirdiau/moviespricecomparison/issues">Report Bug</a>
    ·
    <a href="https://github.com/dsvirdiau/moviespricecomparison/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#assumptions">Assumptions</a></li>
    <li><a href="#tradeoffs">Tradeoffs</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<a href="https://ibb.co/XVRt05B"><img src="https://i.ibb.co/yVjqvkT/Screenshot-2021-07-05-173625.png" alt="Screenshot-2021-07-05-173625" alt="Light"></a><br /><a target='_blank' >Light mode preview</a><br />

<a href="https://ibb.co/crG2pB8"><img src="https://i.ibb.co/9rFyfD4/Screenshot-2021-07-05-173604.png" alt="Screenshot-2021-07-05-173604" alt="Night"></a><br /><a target='_blank' >Dark mode preview</a><br />

### Built With

* [ANDROID STUDIO](https://developer.android.com/studio)
* [GRADLE](https://gradle.org/)



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

* Android Studio
* Android SDK

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/dsvirdiau/moviespricecomparison.git
   ```


<!-- USAGE EXAMPLES -->
## Usage

The app is built keeping in mind MVVM as the architecture for future expandability.

It uses RxJava, Retrofit, Glide, Coroutine and LiveData as components. RxJava is used to zip the api calls together and get a merged result with both the prices coming from FilmWorld and CinemaWorld. Glide is used to load the images from urls. Coroutine is used in the swipe to refresh component to create a 1000ms delay

The architectural elements are ViewModel, View, Model, Repository, Api, ViewModelFactory and Adapter

Repository has been added for future enhancements such as DAO

Data source for the app comes from the following URL - [Movies](https://challenge.lexicondigital.com.au/api/v2/{theatre}/movies)

theatre - cinemaworld or filmworld

API Key used = Yr2636E6BTD3UCdleMkf7UEdqKnd9n361TQL9An7

Access to the web service is authenticated by adding an additional header field: x-api-key.

## Assumptions

Assumptions kept in mind while implementing are as follows:
* ID with prefix cw and fw is always the same for both the api calls
* If an api call fails the following request is mostly successful. Due to this behaviour we retry api calls for a maximum of 3 times just on a safer side to address the server unreliability. Assuming we should receive a response in these tries.
* FilmWorld data structure is used to display information on the main screen with the assumption that both api calls have identical data structure
* Both api calls always return the same(except for id prefix and price) and exact number of records. 

## Tradeoffs

* We only retry for a maximum of 3 times but if the server is still unresponsive it will display an error message as a Toast message

<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/dsvirdiau/moviespricecomparison/issues) for a list of proposed features (and known issues).

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Your Name - [@DevneetVirdi](https://twitter.com/@DevneetVirdi) - geekfreakdeveloper@gmail.com

Project Link: [https://github.com/dsvirdiau/moviespricecomparison](https://github.com/dsvirdiau/moviespricecomparison)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* [ANDROID STUDIO](https://developer.android.com/studio)
* [GRADLE](https://gradle.org/)



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/dsvirdiau/repo.svg?style=for-the-badge
[contributors-url]: https://github.com/dsvirdiau/repo/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/dsvirdiau/repo.svg?style=for-the-badge
[forks-url]: https://github.com/dsvirdiau/repo/network/members
[stars-shield]: https://img.shields.io/github/stars/dsvirdiau/repo.svg?style=for-the-badge
[stars-url]: https://github.com/dsvirdiau/repo/stargazers
[issues-shield]: https://img.shields.io/github/issues/dsvirdiau/repo.svg?style=for-the-badge
[issues-url]: https://github.com/dsvirdiau/repo/issues
[license-shield]: https://img.shields.io/github/license/dsvirdiau/repo.svg?style=for-the-badge
[license-url]: https://github.com/dsvirdiau/repo/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/dsvirdi
[platform-android-shield]: https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white
[platform-android-url]: https://developer.android.com/about
[language-java-sheild]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white
[language-java-url]: https://www.java.com/en/
[language-kotlin-sheild]: https://img.shields.io/badge/kotlin-%230095D5.svg?style=for-the-badge&logo=kotlin&logoColor=white
[language-kotlin-url]: https://developer.android.com/kotlin
[language-csharp-sheild]: https://img.shields.io/badge/c%23-%23239120.svg?style=for-the-badge&logo=c-sharp&logoColor=white
[language-csharp-url]: https://docs.microsoft.com/en-us/dotnet/csharp/