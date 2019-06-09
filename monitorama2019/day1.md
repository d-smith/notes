# Monitorama Day 1

[Video Stream](https://www.youtube.com/watch?v=eZJoevaojyE&t=36s)

## Taking Human Performance Seriously

John Allspaw

Related blog post [here](https://www.adaptivecapacitylabs.com/blog/2017/12/19/taking-human-performance-seriously/)


beliefs about safety 40s - 70s

* safety can be encoded within the design of the tech
* avoid accidents via automation
* procedures can be fully specified and comprehensive, operators just follow the procedures to get work done

Tasks - humans better at some, machines better at others

Thinking changes after 3 mile island - new beliefs

* automation is necessary, also introduces new challenges and risks
* rules and procs always underspecified
* events require humans to make decisions and take actions
* method/models for risk that rely on human judgemen are fraught

What we thought about human comtrib to complex work was wrong

By human performance we mean cognition

Study cog work by studying incidents

* time pressure
* high or increasing consequences
* etc

methods, approaches, and techniques

People bring... 

knowledge of what's going on in the world
tools
alerts
alert history
recent changes in tech
new dependencies
time series data
what's been investigated
observations and hypothesis changed
time of year. day of year, time of day
logs

Gary Klien et al [Problem Detection](https://www.researchgate.net/publication/220579480_Problem_detection)

Different tenures, domain expertise, etc

Multiple threads of activity - some productive, so not

* People wil pursue what they think will be productive

Layer

* tentative evolving shared hypothesis
* anaomolous signals and reps

[Approaching Overload](https://www.researchgate.net/publication/333091997_Approaching_Overload_Diagnosis_and_Response_to_Anomalies_in_Complex_and_Automated_Production_Software_Systems) marisa grayson approaching overload ohio state university

monitoring/observability are inextritably coupled with other activies

Recommendation

* build your own internal resources to do inciedent analysis, cadre, etc
* ask better questions - how do you improve tools, what tricks to understand how opaque 3d party services work, are there other sources of into you can tap

* select a few incidents for closer and deeper analysis - look for suprises, ambiguity, use disagreements about incidents as data
* build or adjust tooling to capture data streams of incidents and their handling - chats, call transcripts, command people ran, hypothesis that didn;t pan out, etc
* make company-wide postmortem sesions regular events (investments you didn't plan on making)

Conclusions

* understanding cogitive work in software env and ops crtically important
* doing this well will mean new language, concepts, paradigms, and practices
* must be driven by both research/academia and industry/practitioners

Lisanne Bainbridge [The ironies of automation](https://www.ise.ncsu.edu/wp-content/uploads/2017/02/Bainbridge_1983_Automatica.pdf)

The more advanced an automation, the more crucial the contribution of the human to the system.

adaptivecapacitylabs.com/blog

## Chaos Engineering Traps
Nora Jones

observabiity - feedback that provides insigt into a process and refers to the work needed to extract meaning form avilable data

woods and hollnagel, jint cognitive systems

Apollo 1: launch rehearsal test

we can use ce to build adaptive decision making capabilities in engineers

The 8 traps of ce

3 accident investigation traps talk

Trap 1 - you can measure you success with ce by counting the number of vulerabilities you find.

you don;t knw much about safety by counting error Wears

How to lie with statistics - darrell huff

GOal of ce - puh forward on a journey of resilience through the vulnerailities we find

Looking at what went right in a ce experiment helps us undertandd what went eromg

trap 2 - not all the people that work on the service need to be involved in the experiment and the preperation

trap 4 - chaos engineers should be rule enforcers

build relationships, you want people to share with you, want to understand the tradeoffs people make under pressure
don;t mandate, take control, bring context and influence

trap 4.5 fix the vulnerabilities you find, and if you can, automate the fixes

Trap 5 - not real CE unless you move beyonf gamedays amd experimenting in sandbox environments

you can get more vslue going through the proes of automating the experiments than you actually get automating...

Trap 6 - the most important part of ce is running the experiment

Before, during, after the experiment all phsese desrve equel experimnt

Before = all models are wrong, but some are useful

Trap 7 - you can worry about safety later. The important part if creating the chaos.

Vulnerable defences

creating the chaos i easy, thinkig about safety is hard

Trap 8 - you can do chao seng without having an undertanding of hte system you are experimenting on.

Trap 8.5 it's ok to be a little lenient on the definition of steady state

Trap 3 - there is a presciptive formulat for doing CE

no preseriptive formula for dealing with the unknown and learning



## Observability and performance analysis with BPF

David Calavera

ak RTITRry new questions and explore where the cookie crumbs take you - observability def, charity majors

build robustness against negative events

key: access to the raw data

but... what if you don't think about the rquestio you want to ask

how can we get as micj cata as we want from a linux system?
BPF - in kernel virtual machine to run event driven programs

* llvm backend to compile C code to BPF instructions

github.com/iovisor/bpftrace

tracepoint:syscall:sys_enter_execva

tracepoint -> kernel hook
syscalls - subsystem
sys_enter_execve - event name

Observe how  program behaveds from the kernel's point of view

Blog post: Docker flamegraphs

on-CPU flame graphs w/BPF

github.com/iovisor/bcc

off cpu profiles - what's going on when you are waiting on IO, etc

flame graph - cpf

THe only way to mesure total timing is by combing on-cpu and off-cpu

Don't be afraid tp push boundaries to find better answers

github.com/brendangreg/FlameGraph

## Sponsor Talks

Resilience driver development

hows does it react when it's down? 
aasertion -> monitoring (query your monitoring tool)
pessimistic - not like unit tests where you assume timely reponses, etc


## The Power and Creativity of PostMortems and Repair Items

Nida Farrukh

postmortem -  data driven argument for change

outages - opportunity

postmortem

* Impact assessment - customer and business focus
* timeline
* analysis of contributing factors
* repair items (aka action items)

When to do a pm

* something borke you and your ustomers are about -now you know more abouit user behavior
* something that you expected to work - system cannot handle redl world use case

PM pinpoints where you need attention

Outage is an actual case study of how your system responds in the real world

--> Outage demonostrates what needs to change. PM provides the specifics for the change

impact assessment

* user focused
* hard numbers - cost of outagei nterms of failed requests, txns that did not complete, what teh cose of the outage was
  
Repair item categories = detection, mitigation, fixing

SRE focus on mitigation and detection
Devs focus on fixes

We focus on what we know - involve people from across your teams to brainstorm repair items

Tool - timeline:

TTX metrics - time to detect, engage, mitigate

impact ... detection ... engagement ... mitigation

high time to detect

- automatically detect? missing alert coverage?
- alerting thresholds need adjustment?
- alerting on the right things?

High time to engage?

- comm processes need improvement?>
- tech underlying comm system unreliable/slow
- did we need to engage specific people, and they weeren'y available?

High time to mitigate?

- too much manual work? error prone? no rollback?
- too time consuming to diagnose? IS diagnostiv info not available/acessible?
- system is not clearly documented? arw certain people SPOFs of information/action?

Questions to guide fix repair itmes

- why do we event X?
- hae we missed a use case during design
- can user flows by changed so the same failure has lessimpact?>
- do our systems have degraded modes to switch to?
- are our dependencies behaving in accordance with expectations?

pm - an arg and opp for change

## A Story of One SLO

Narrative on how to carefully construct an SLO

## The AWS Billing Machine and Optimizing Cloud Costs

Ryan Lopopolo

Efficiency engineering

Thesis: cost optimization is observability, cost and performance is aligned.

[EC2 Info](https://ec2instances.info/)

Monitoring pricing/etc - fire event to slack for manual approve, action, etc.

[Stripe Blog Entry](https://stripe.com/blog/aws-reserved-instances)

## Explain it Like I’m Five - What I Learned Teaching Observability to My Kids

Dave Cadwallader 

Teach our tools in ways that are understandable and accessible for children.

Prometheus - monitor garage door... so bears don't eat your trash

CLean thresholds, when is something urgent, avoiding supurious alarms, firing alerts, resolving alerts.

phippy.io

phippy and zee go to the mountains

@PhippyMountains

range vector, moving average - smooth it out

bluetooth moisture sensors

## Automation + Artisanship: the Future of Runbooks

Kenny Johnston

Artisans drive local economies as globalization expands - [don't mock the pickle maker](https://www.nytimes.com/2012/02/19/magazine/adam-davidson-craft-business.html)

* take pride in your work
* thank your tools

Drive - Daniel Pink

* Activities that require deep cognitive work not motivated by external incentives
* Intrinsic motivators - autonomy, mastery, purpose

Automation

* Hacks
* Bots
* Operators
* Scripts
* Process Documentation
* Runbooks

Runbook - a compilation of routine procedures and operations that the system administrator or operator carries out.

* Becoming more common to see them checked into the repository
* Becoming more common to be executable
* Becoming common to be written before production

The Conflict: 

* Automation burners: We're being robbed of our ability to be artisans
* vs SRE book maximalist PoV - we should automate everything

THe Cure

* Find a balance where people can have mastery yet provide the benefits and scale of automation
* Runbook Zero (like inbox zero principles)
    * completeness - capture everything, runbook in source, capture data around how often run, etc
    * prioritization - prioritize with data, product management perspective (how often run, risk, etc)
    * iterative process - iterate on a minimally viable runbook, add automation overtime based on data

Continuous feedback loop for your observability and response

## Tradeoffs on the Road to Observability

Liz Fong-Jones

Get the right incentives in place

Why 20 ways of doing things

* Not invented here
* Chrome polishing, pet projects, resume driven development
* Just trust the consultants (e.g. no choice in your tooling)

Want: appropriate reliability and scalability

* Scalability of humans (no burnout) and machines

Appropriate observability: how we archieve scalability and reliability

* Take the status and make a decision

Get out of the busines of feeling important because there's a line of people waiting on you.

Engineering != writing software

* Art of solving problems

People have the wrong incentives, build the wrong software, people burn out operating it, users are unhappy

Change the incentives

* How do we reward people... talk about success cases, what was the porblem, what did we try, how did we solve it, how can others make use of this work, did we save work and make the system better
* Don't glorify creating complexity - can we reuse others code, open source, leverage standards

What problem are your customers having?

* Keep asking why... uncover the deeper seated need

What options did you consider?

* Share and educate

How to build the right software

* Collaborate within your own organization
* Look for similar problems
* Document and provide examples
* Upskill each other
* User confusion is costly - make things simple, easy
* Share tools and code
* Use common idioms (open tracing and telemetry)
* Do not dump your hacks
* Explain your full context
* Upstream core and specs
* Have external maintainers
* Document your technical decisions

Success does not demand heroism

Look. Evaluate. Collaborate. Share.