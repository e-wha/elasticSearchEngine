ARG ELASTIC_VERSION

# https://www.docker.elastic.co/
FROM docker.elastic.co/logstash/logstash:${ELASTIC_VERSION}

# Add your logstash plugins setup here
# Example: RUN logstash-plugin install logstash-filter-json
COPY postgresql-42.7.5.jar /usr/share/logstash/logstash-core/lib/jars/postgresql.jar
COPY tour_template.json /usr/share/logstash/tour_template.json