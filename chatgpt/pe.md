# Prompt Engineering

## Intro

Misc

* Via deeplearing.ai course Prompt Engineering for Developers

Two types of LLMs

* Base LLM - predict next work
* Instruction Tuned LLM - tries to follow instructions
    * Trained starting with a base llm with inputs/outputs that show good attempts at following instructions
    * reinforcement learning with human feedback

## Prompting Principles

* Avoid prompt injections
* Model does not always know the boundary of its knowledge, where it hasn't perfectly memorized data it has seen during training.


### Principal 1: Give Clear Instructions
Tactics

1. Use delimeters
2. Ask for structured output
3. Check model to see if assumptions are satisfied
4. Few-shot prompting

### Principal 2: Give the model time to "think"

1. Specify the steps required to complete a task
2. Instruct the model to work out its own solution before rushing to a conclusion


