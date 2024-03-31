# Angular - Pluralsight

## Course 1 - What is Angular by Hampton Paulk

Angular is for making websites

* Prewritten code
* Useful tools
* Structured guidelines
* Packaged to create web apps

=> web framework

Building Blocks

* Components
* Templates - static html mixed with dynamic data
* Directives - add logic to templates
* Services - provide functionality not tied to just one component

Typescript

[Example Project](https://stackblitz.com/edit/angular-pie-shop?file=README.md)

* Index file - entry point or base of the app

Components - should be small, focused, one responsibility

Component files

* typescript file - functionality
* html file (template) - all html elements and view logic
* css file (style) - makes it look good


App component

Decorator - metadata that tells Angular how to create the component

```typescript
@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    standalone: true,
    imports: [RouterOutlet, TopBarComponent]
})
```

selector - used as an element, for example index.html might reference the application component as `<app-root></app-root>`

templateUrl - path to the html file
styleUrls - path to the css file(s) defining look of the component

standalone - ng modules not used to define the template dependencies

imports - dependencies

    RouterOutlet - directive that tells Angular where to put the components (component destination)

Templates

* what the user sees
* ties to the component

main.ts - entry point into the application, loads app config, config
file references the routing config

Directives

* Enables the use of logic in templates
    * Special instructions in-line with the elements
    * Enables common programming tasks
    * Conditionally enable visibility

Data binding - bind data from objects to elements on the page

* Interpolation - {{ expression }}
* Property binding - [property]="expression"
* Event binding - (event)="expression"
* Two-way binding - [(ngModel)]="expression"

Pipes - transform data in the template

* {{ expression | pipeName: value }}
* Angular has built-in pipes for common tasks

Services - share code and data between components

* Fetch data from an API
* Manage authentication
* Implement business logic 



## Course 2 - Angular The Big Picture by Joe Eames

Goals - know angular's capabilities, strengths, and weaknesses

What is angular?

* a tool to help you build websites
* tools to
    * communicate with servers
    * maintain state
    * ease display of data
    * sync state with changes
    * package apps
    * improve performance


Goals

* Standards-based
* Modern
* Performant


