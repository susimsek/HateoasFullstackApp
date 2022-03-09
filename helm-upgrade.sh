#!/bin/bash
# Files are ordered in proper order with needed wait for the dependent custom resource definitions to get initialized.
# Usage: bash helm-apply.sh

suffix=helm
name=hateoas-fullstack
namespace=hateoas-fullstack
helm dep up ./deploy/${suffix}
helm upgrade --install ${name} ./deploy/${suffix} --namespace ${namespace}


