{{- define "ui.selectorLabels" -}}
app: {{ template "app.shortname" . }}-ui
release: {{ .Release.Name }}
{{- end }}

{{- define "ui.labels" -}}
chart: {{ include "app.chart" . }}
{{ include "ui.selectorLabels" . }}
heritage: {{ .Release.Service }}
app: {{ template "app.shortname" . }}-ui
{{- end }}