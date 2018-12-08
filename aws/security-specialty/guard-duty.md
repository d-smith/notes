# Amazon Guard Duty

https://www.youtube.com/watch?v=Imjbh0WPSR4

Thread Detection Service Re-Imagined for the Cloud

* Continuously monitors and protects AWS accounts, along with the applications and services running within them.
* Detects known and unknown threats (zero-days)
* Makes use of AI and machine learning
* Integrated threat intelligence
* Operates on CloudTrail, VPC Flow Logs, DNS
* Detailed and actionable findings

Regional service - enable it in all regions

Detecting Known Threats

* GuardDuty consumes feeds from various sources
    * AWS Security
    * Commercial feeds
    * Open source feeds
    * Customer provided thread intel

* Known malware infected hosts
* Anonynizing proxies
* Sites hosting malware and hacker tools
* Cryto-currency mining pools and wallets
* Great catch-all for suspicious and malicious activity

Detecting Unknown Threats

* Anomoly Detection

    * Algs to detect unusual behavior
        * Inspecting signal patterns for signatures
        * Profiling normal and looking at deviations
        * Machine learning classifiers

    * Larger R&D effort
    * Develop theoretical models
    * Experiment with implementations
    * Testing, tuning, and validation

    step: 13:30