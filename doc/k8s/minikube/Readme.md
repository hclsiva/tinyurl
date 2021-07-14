# Minikube basic commands
## To start a minikube

minikube start

## To see the cluster info

kubectl cluster-info

## To see minikube dashboard

minikube dashboard

# PODs

## To deploy the application manually
kubectl create -f tinyurl-manual.yaml

## Seeing your newly created pod in list of pods
kubectl get pods

## Retrieving the whole definition of a running Pod

kubectl get pods tinyurl-manual -o yaml

kubectl get po tinyurl-manual -o json

## Retrieving a Pod's Log with kubectl Logs
To see your pod’s log (more precisely, the container’s log) you run the following command
on your local machine (no need to ssh anywhere):

kubectl logs tinyurl-manual

## Specifying the container name when getting logs of a multi-container Pod
kubectl logs tinyurl-manual -c tinyurl

## Forwarding a local network port to a port in the Pod
kubectl port-forward tinyurl-manual 8888:8080

Then run, 

http://localhost:8888/greeting/sayHello?name=Siva

http://localhost:8888/tinyurl/health

# Labels

## List the pods and its labels
kubectl get pods --show-labels

kubectl get po -L creation_method,env

## Modifying labels of existing pods
kubectl label po tinyurl-manual creation_method=manual

NOTE :: You need to use the --overwrite option when changing existing labels.

kubectl label po tinyurl-manual-v2 env=debug --overwrite

## Label Subsets
kubectl get po -l creation_method=manual
kubectl get po -l env
kubectl get po -l '!env'

# Annotations

## Sample Annotation
kubectl annotate pod tinyurl-manual mycompany.com/someannotation="foo bar"

kubectl describe pod tinyurl-manual

# Namespaces

## To get the list of Namespaces
kubectl get ns

## Get the list of pods in the namespace 
kubectl get pods --namespace kube-system

## To create a namespace
kubectl create namespace custom-namespace
kubectl create -f custom-namespace.yaml

# Delete Pods

## To delete pods by name
kubectl delete pods tinyurl-gpu

## To delete pods by label
kubectl delete pods -l creation_method=manual

## To delete pods by namespace
kubectl delete pods ns custom-namespace

## To delete all pods
kubectl delete po --all

## To delete everything
kubectl delete all --all

