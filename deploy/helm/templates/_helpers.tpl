{{/*
Create a default fully qualified name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
*/}}
{{- define "app.fullname" -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "app.shortname" -}}
{{- $name := (.Values.nameOverride | default (printf "%s" .Release.Name)) -}}
{{- printf "%s" $name -}}
{{- end -}}

{{- define "app.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" -}}
{{- end }}