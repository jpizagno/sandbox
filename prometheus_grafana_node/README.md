see notes at end of this link:
https://www.digitalocean.com/community/tutorials/how-to-install-prometheus-using-docker-on-ubuntu-14-04

### prometheus

set your IP_ADDRESS in prometheus.yml
export IP_ADDRESS=`ip addr show wlan0 | grep -Po 'inet \K[\d.]+' | head -n 1`
sed -i 's/your_ip_address/'"$IP_ADDRESS"'/' prometheus.yml

# start:
shell% ./docker_run_prometheus.sh

# after starting, metrics are here
http://192.168.2.106:9090/targets

### Node-Exporter
Node-Exporter will collect metrics about host system.

# start:
shell% docker_run_nodeexporter.sh

## run Grafana

grafana will create dashboards for metrics, and it is already setup for prometheus as a datasource

# setting up Prometheus data source in grafana:
http://prometheus.io/docs/visualization/grafana/#using

To create a Prometheus data source:

    1) Click on the Grafana logo to open the sidebar menu.
    2) Click on "Data Sources" in the sidebar.
    3) Click on "Add New".
    4) Select "Prometheus" as the type.
    5) Set the appropriate Prometheus server URL (for example, http://ip_server_fromip_server_from_prometheus:9090/)
    6) Adjust other data source settings as desired (for example, turning the proxy access off).
    7) Click "Add" to save the new data source.

Creating a Prometheus graph

Follow the standard way of adding a new Grafana graph. Then:

    1) Click the graph title, then click "Edit".
    2) Under the "Metrics" tab, select your Prometheus data source (bottom right).
    3) Enter any Prometheus expression into the "Query" field, while using the "Metric" field to lookup metrics via autocompletion.
    4) To format the legend names of time series, use the "Legend format" input. For example, to show only the method and status labels of a returned query result, separated by a dash, you could use the legend format string {{method}} - {{status}}.
    5) Tune other graph settings until you have a working graph.


# run
shell% ./docker_run_grafana.sh
