# wildfy jgroups infinispan clustering sample

## Major info
  modify standalone-full-ha.xml
  ```xml
          <subsystem xmlns="urn:jboss:domain:infinispan:2.0">
                ...
                <cache-container name="web" default-cache="dist" module="org.wildfly.clustering.web.infinispan">
                    <transport lock-timeout="60000"/>
                    <distributed-cache name="dist" mode="ASYNC" batching="true" l1-lifespan="0" owners="2">
                        <file-store/>
                    </distributed-cache>
                </cache-container>
                ...
            </subsystem>
  ```
  or use `jboss-cli.sh`
  ```shell
  vim caches.cli

  /subsystem=infinispan/cache-container=TEST:add(default-cache=x)

  /subsystem=infinispan/cache-container=TEST/local-cache=x:add()
  /subsystem=infinispan/cache-container=TEST/local-cache=x/component=eviction:add(max-entries=3,strategy=LRU)

  /subsystem=infinispan/cache-container=TEST/local-cache=y:add()
  /subsystem=infinispan/cache-container=TEST/local-cache=y/component=eviction:add(max-entries=5,strategy=FIFO)

  ./jboss-cli.sh -c --file=caches.cli
  ```


## Run
  ```shell
  $JBOSS_HOME/bin/standalone.sh -c standalone-full-ha.xml -b $IPADDR -Djboss.node.name=node-$IPADDR -Djboss.messaging.cluster.password=$PASSWD -Djboss.node.key=$PASSWD
  ```
  e.g.
  ```shell
  ./standalone.sh -c standalone-full-ha.xml -Djboss.node.name=node1 -Djboss.messaging.cluster.password=zzz -Djboss.node.key=zzz
  ./standalone.sh -c standalone-full-ha.xml -Djboss.node.name=node2 -Djboss.messaging.cluster.password=zzz -Djboss.node.key=zzz
  ```

## use Nginx/Apache load balance
