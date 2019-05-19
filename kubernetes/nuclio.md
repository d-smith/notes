# Nuclio Notes

* Start kubenetes via docker
* Verify kubectl

Secrets - see [here](https://kubernetes.io/docs/concepts/configuration/secret/)

K8s guide - see [here](https://github.com/nuclio/nuclio/blob/master/docs/setup/k8s/getting-started-k8s.md)

Kubernetes RBAC - https://kubernetes.io/docs/reference/access-authn-authz/rbac/

Nuclio port forwarding

kubectl port-forward -n nuclio $(kubectl get pods -n nuclio -l nuclio.io/app=dashboard -o jsonpath='{.items[0].metadata.name}') 8070:8070

Getting started - dashboard, new project, hello template, test event, deploy