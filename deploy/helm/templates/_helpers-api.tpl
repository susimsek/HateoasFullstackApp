{{- define "api.selectorLabels" -}}
app: {{ template "app.shortname" . }}-api
release: {{ .Release.Name }}
{{- end }}

{{- define "api.labels" -}}
chart: {{ include "app.chart" . }}
{{ include "api.selectorLabels" . }}
heritage: {{ .Release.Service }}
app: {{ template "app.shortname" . }}-api
{{- end }}