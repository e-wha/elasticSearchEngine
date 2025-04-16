# KH_elasticSearchEngineProject
KH정보교육원 엘라스틱 서치 검색엔지 프로젝트


사용자 비밀번호 변경
curl -X POST "localhost:9200/_security/user/kibana_system/_password" -H "Content-Type: application/json" -u elastic:elasticsearch -d "{\"password\": \"elasticsearch\"}" 

curl -X POST "localhost:9200/_security/user/logstash_internal/_password" -H "Content-Type: application/json" -u elastic:elasticsearch -d "{\"password\": \"elasticsearch\"}" 

사용자 생성 확인
curl -X GET "localhost:9200/_security/user/logstash_internal" -u elastic:elasticsearch

클러스터 상태 확인
curl -X GET "localhost:9200/_cluster/health?pretty" -u elastic:elasticsearch

사용자 인증 확인
curl -X GET "localhost:9200/_search?pretty" -u logstash_internal:elasticsearch
