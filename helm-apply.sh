#!/bin/bash
# Files are ordered in proper order with needed wait for the dependent custom resource definitions to get initialized.
# Usage: bash helm-apply.sh

suffix=helm
name=hateoas-fullstack
namespace=hateoas-fullstack
kubectl create namespace ${namespace}
helmVersion=$(helm version --client | grep -E "v3\\.[0-9]{1,3}\\.[0-9]{1,3}" | wc -l)

  if [ $helmVersion -eq 1 ]; then
helm uninstall ${name} 2>/dev/null
  else
helm delete --purge ${name} 2>/dev/null
  fi
helm dep up ./deploy/${suffix}
  if [ $helmVersion -eq 1 ]; then
helm install ${name} ./deploy/${suffix} --replace --namespace ${namespace}
  else
helm install --name ${name} ./deploy/${suffix} --replace --namespace ${namespace}
  fi
