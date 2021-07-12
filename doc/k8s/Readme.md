# How to Create a Cluster?

https://www.youtube.com/watch?v=3EF4fXZcTDg

# Github Action to deploy in GKE Cluster

## How to deploy a spring boot application with docker in k8s cluster

https://www.youtube.com/watch?v=ygFU7jjuojc
https://www.youtube.com/watch?v=_Vy631QpfI0

## Sample github 

https://github.com/aeradron-code/github-actions-gke

# To run kubectl commands against a cluster created in Cloud Console
gcloud --project punchbag container clusters get-credentials --zone us-central1-c my-first-cluster

# To Retrieve Additional Data from the nodes
kubectl describe node gk3-autopilot-cluster-1-default-pool-1529139d-cnc7

# To Run the docker image in the GKE cluster
kubectl run tinyurl --image=hclsiva/tinyurl --port=8080

# Create Deployment inside GKE Cluster
kubectl create deployment tinyurl --image hclsiva/tinyurl:latest

# To Expose a service
kubectl expose deployment/tinyurl --type="LoadBalancer" --port 8080 --target-port=8080 --name=tinyurl-service --type=LoadBalancer

