<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
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
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
# Split Configuration

[![Product Name Screen Shot][product-screenshot]](https://example.com)

Automated test framework,  for an automated test suite that can be run on 
both the environments (I have for the PROD environment https://demo.guru99.com/v4 
which has a STAGING environment, reachable through https://demo.guru99.com/ ) with different configurations with the change of a parameter or command.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With
Java like programming language and yaml for CI/CD.
Below the list of major frameworks/libraries used in the project.

* [<img height="40" src="/Users/jojozeufack/Downloads/Selenium-automation-testing1.png" title="Selenium" width="60"/>][Selenium-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/TESTNG.png" title="TestNG" width="60"/>][Behave-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/Docker.webp" title="Docker" width="60"/>][Docker-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/Gitlab.png" title="GitLab" width="60"/>][GitHub-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/zaproxy.png" title="Zaproxy" width="60"/>][GitHub-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/postman.jpeg" title="Postman" width="60"/>][GitHub-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/pynt.png" title="Pynt" width="60"/>][GitHub-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/Maven.png" title="Maven" width="60"/>][GitHub-url]
* [<img height="40" src="/Users/jojozeufack/Downloads/pmd_logo.webp" title="Pmd" width="60"/>][GitHub-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

Instructions on setting up the project locally.
To get a local copy up and running follow these steps.

### Prerequisites

Have Java8 or above installed on your OS. Let see how to install it.
On Mac:
  ```sh
  brew install java
  ```

On Linux:
  ```sh
  sudo apt install default-jdk
  ```
On Windows:
1.Open the command prompt by pressing the "Windows Key + R" and typing "cmd".
2.Download the Java Development Kit (JDK) installer from the Oracle website.
3.Navigate to the location of the downloaded JDK installer in the command prompt.
4.Run the installer using the following command:
java -jar jdk-<version>-windows-x64.exe
5.Follow the prompts in the installer to complete the installation.

Verify the installation by typing the following command:
java -version

### Installation

1.Clone the repo
   ```sh
   git clone git@gitlab.com:zeufi/logispin_question2.git
   ```
2.Install all dependencies
  Right-click on the pom.xml file, go on Maven then click on "Reload project"


<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage
To run the framework locally:
mvn test -Pchrome -Denv=prod # Will run on the PROD env
mvn test -Pchrome -Denv=staging # Will run on the STAGING env

To run the framework on GitLab CI/CD pipeline:
1.Just push the code on master or staging branch the pipeline will automatically 
start on that branch and all the tests relative on that branch will run on both 
chrome and firefox.

After the running a test report will be generated in the test-output folder
to analyze eventually fails or skips and why.

In addition to jobs for running test on firefox and chrome I have had the following:
  - code quality (static code analysis)
  - unit tests (dynamic code analysis)
  - api testing
  - api security testing
  - website security test
  - api page (report page gitlab)

### Notes
More of times the jobs on Chrome browser fails to run the tests due to 
chrome securities and chromedriver.
For more examples on how to run the framework and other jobs in the 
pipeline, please refer to the file [Notes.txt]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Fix the job for tests running on Chrome browser
- [x] Send email report to the team
- [ ] Add Additional browser to test (like Edge, Safari)
- [ ] Customize the report send by email base on the browser
    - [ ] Chrome
    - [ ] Firefox

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this framework better, please fork the repo and create a pull request. 
You can also simply open an issue with the tag "enhancement".

1. Fork the Project
2. Create your Feature Branch
3. Commit your Changes
4. Push to the Branch
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Your Name - [@my_twitter](https://twitter.com/Zeufi01) - zeufackjojo@yahoo.fr

Project Link: [https://github.com/zeufi/Logispin_Question1](https://github.com/zeufi/Logispin_Question1.git)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Use this space to list resources you find helpful and would like to give credit to. I've included a few of my favorites to kick things off!

* [Selenium](https://www.selenium.dev/)
* [Python](https://www.python.org/)
* [GitHub Pages](https://pages.github.com)
* [Docker](https://www.docker.com/)
* [Behave](https://behave.readthedocs.io/en/stable/#)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: https://wcc.co.uk/wp-content/uploads/2016/05/Automation-Test-Architect.jpg
[Selenium.js]: https://www.webomates.com/wp-content/uploads/2019/12/Selenium-automation-testing1.png
[Selenium-url]: https://www.selenium.dev/
[Behave.js]: https://behave.readthedocs.io/en/latest/_images/behave_logo1.png
[Behave-url]: https://behave.readthedocs.io/en/stable/
[Docker.js]: https://www.docker.com/wp-content/uploads/2022/03/vertical-logo-monochromatic.png
[Docker-url]: https://www.docker.com/
[GitHub.io]: https://cdn.invicti.com/statics/img/drive/h2jfrvzrbyh1yff2n3wfu2hkqqps6x_uvqo.png
[GitHub-url]: https://pages.github.com
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 