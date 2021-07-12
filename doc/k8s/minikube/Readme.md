# To start a minikube

minikube start

# To see the cluster info

kubectl cluster-info

# To see minikube dashboard

minikube dashboard

# To deploy the application manually
kubectl create -f tinyurl-manual.yaml

# SEEING YOUR NEWLY CREATED POD IN THE LIST OF PODS
kubectl get pods

# RETRIEVING THE WHOLE DEFINITION OF A RUNNING POD

kubectl get pods tinyurl-manual -o yaml

kubectl get po tinyurl-manual -o json

# RETRIEVING A POD’S LOG WITH KUBECTL LOGS
To see your pod’s log (more precisely, the container’s log) you run the following command
on your local machine (no need to ssh anywhere):

kubectl logs tinyurl-manual

# SPECIFYING THE CONTAINER NAME WHEN GETTING LOGS OF A MULTI-CONTAINER POD
kubectl logs tinyurl-manual -c tinyurl

# FORWARDING A LOCAL NETWORK PORT TO A PORT IN THE POD
kubectl port-forward tinyurl-manual 8888:8080

Then run, 

http://localhost:8888/greeting/sayHello?name=Siva

http://localhost:8888/tinyurl/health

# List the pods and its labels
kubectl get pods --show-labels

kubectl get po -L creation_method,env

# Modifying labels of existing pods
kubectl label po tinyurl-manual creation_method=manual

NOTE :: You need to use the --overwrite option when changing existing labels.

kubectl label po tinyurl-manual-v2 env=debug --overwrite

# Label Subsets
kubectl get po -l creation_method=manual
kubectl get po -l env
kubectl get po -l '!env'

