# Computer Networks Interview Notes

Source checklist: [Love Babbar Networking Cheatsheet](https://whimsical.com/networking-cheatsheet-by-love-babbar-FcLExFDezehhfsbDPfZDBv)

Use this as a compact OA/interview revision sheet. For every topic, know: definition, where it fits in OSI/TCP-IP, one example, and one tradeoff.

## 1. Basics

Link: [Cloudflare - What is a computer network?](https://www.cloudflare.com/learning/network-layer/what-is-a-computer-network/)

- **Computer network:** connected devices that exchange data using protocols.
- **Node/Host:** any network-connected device. A host usually has an IP address.
- **Client:** requests service/data.
- **Server:** provides service/data.
- **Peer:** device that can act as both client and server.
- **Packet:** network-layer unit of data; contains headers plus payload.
- **Frame:** data-link-layer unit; contains MAC addresses.
- **Bandwidth:** maximum capacity of a link, usually bits/sec.
- **Throughput:** actual achieved data rate.
- **Latency:** time taken for data to travel from source to destination.
- **Jitter:** variation in packet delay.
- **Bit rate:** number of bits transmitted per second.
- **Noise:** unwanted signal interference.
- **Attenuation:** signal strength loss over distance.
- **Distortion:** signal shape changes during transmission.
- **Localhost:** current machine, commonly `127.0.0.1` or `::1`.

Interview line: bandwidth is capacity, throughput is actual output, latency is delay, jitter is delay variation.

## 2. Web vs Internet

Link: [MDN - How the web works](https://developer.mozilla.org/en-US/docs/Learn_web_development/Getting_started/Web_standards/How_the_web_works)



- **Internet:** global network infrastructure connecting networks.
- **Web/WWW:** service running on the internet using HTTP/HTTPS, browsers, URLs, and web servers.
- **Website:** collection of web pages/resources.
- **Web page:** individual HTML document/resource.

Interview line: the web is one application of the internet; email, FTP, SSH, VoIP also use the internet.

## 3. Client-Server Architecture

- **Client-server architecture:** a network model where clients request services/resources and servers provide responses.
- **Client:** user-side device/app like browser, mobile app, or desktop app.
- **Server:** machine/program that stores data, runs logic, and sends responses.
- Flow: client sends request -> server processes it -> server sends response.
- Example: browser requests `google.com`; Google's server returns the web page.

Pros: centralized control, easier security, easier data management.
Cons: server failure can affect many clients; high traffic needs scaling/load balancing.

Interview line: client initiates communication, server listens and responds.

## 4. Transmission Media

Link: [GeeksforGeeks - Transmission media](https://www.geeksforgeeks.org/computer-networks/types-transmission-media/)

- **Guided media:** physical path.
  - Twisted pair: cheap LAN cable, affected by EMI.
  - Coaxial cable: better shielding, older cable TV/LAN usage.
  - Fiber optic: high speed, long distance, immune to EMI.
- **Unguided media:** wireless.
  - Radio waves: Wi-Fi, cellular.
  - Microwaves: point-to-point links, satellite.
  - Infrared: short range, line-of-sight.

OA angle: fiber has high bandwidth and low loss; twisted pair is common and cheap.

## 4A. Digital and Analog Transmission

### Digital-to-Digital Transmission

Digital-to-digital transmission means converting **digital data** into **digital signals** for transmission over a medium.

Common techniques:

| Technique | Idea | Example/Use |
|---|---|---|
| Line coding | Converts bits directly into signal patterns. | NRZ, Manchester, Differential Manchester |
| Block coding | Adds extra bits for error detection/synchronization. | 4B/5B, 8B/10B |
| Scrambling | Replaces long sequences of same bits to maintain synchronization. | B8ZS, HDB3 |

Important points:
- Used when both data and signal are digital.
- Receiver must know bit timing/synchronization.
- Manchester encoding helps synchronization but needs more bandwidth.

Interview line: digital-to-digital encoding converts 0s and 1s into digital voltage/light/radio signal patterns.

### Analog-to-Analog Transmission

Analog-to-analog transmission means converting **analog data** into **analog signals**, usually by modulation.

Common modulation techniques:

| Technique | Full form | What changes? |
|---|---|---|
| AM | Amplitude Modulation | Amplitude of carrier wave changes |
| FM | Frequency Modulation | Frequency of carrier wave changes |
| PM | Phase Modulation | Phase of carrier wave changes |

Important points:
- Used for radio/TV broadcasting and voice transmission.
- Carrier signal is modified according to original analog data.
- Modulation helps send low-frequency data over long distances.

Interview line: analog-to-analog modulation changes amplitude, frequency, or phase of a carrier wave.

## 5. Network Devices

Link: [Cisco - Network devices](https://www.cisco.com/c/en/us/solutions/small-business/resource-center/networking/networking-basics.html)

| Device | Layer | Main job |
|---|---:|---|
| Repeater | Physical | Regenerates weak signal |
| Hub | Physical | Broadcasts bits to all ports |
| Bridge | Data Link | Connects LAN segments using MAC addresses |
| Switch | Data Link | Forwards frames using MAC table |
| Router | Network | Routes packets between networks |
| Gateway | Multiple | Translates between protocols/networks |
| Modem | Physical/Data Link | Modulates/demodulates ISP signal |
| Access Point | Data Link | Connects wireless clients to wired network |
| Firewall | Network/Transport/App | Filters traffic by rules |

Interview line: switch works with MAC addresses inside a LAN; router works with IP addresses between networks.

## 6. Unicast, Broadcast, Multicast, Anycast

Link: [Cloudflare - What is anycast?](https://www.cloudflare.com/learning/cdn/glossary/anycast-network/)

- **Unicast:** one sender to one receiver. Example: browsing a website.
- **Broadcast:** one sender to all devices in local network. Example: ARP request.
- **Multicast:** one sender to a selected group. Example: IPTV, routing updates.
- **Anycast:** one IP served by multiple locations; nearest/healthiest server responds. Example: DNS/CDN.

OA angle: broadcast does not cross routers by default.

## 7. Network Topologies

Link: [GeeksforGeeks - Network topologies](https://www.geeksforgeeks.org/computer-networks/types-of-network-topology/)

- **Bus:** all devices share one backbone. Cheap, collision-prone, hard fault isolation.
- **Star:** all devices connect to central switch/hub. Easy management, central device is critical point.
- **Ring:** each device connects to two neighbors. Predictable path, break can affect network.
- **Mesh:** many/all devices interconnect. Reliable, expensive.
- **Tree:** hierarchical star/bus mix. Scalable, root/backbone failure hurts.
- **Hybrid:** combination of topologies.

Interview line: modern LANs commonly use star topology with switches.

## 8. LAN, MAN, WAN, PAN

Link: [Cloudflare - LAN vs WAN](https://www.cloudflare.com/learning/network-layer/what-is-a-lan/)

- **PAN:** personal area, Bluetooth hotspot devices.
- **LAN:** local area, home/office network.
- **MAN:** city-scale network.
- **WAN:** large geographic network; the internet is the biggest WAN.

## 9. OSI Model

Link: [Cloudflare - OSI model](https://www.cloudflare.com/learning/ddos/glossary/open-systems-interconnection-model-osi/)

Mnemonic bottom to top: **Please Do Not Throw Sausage Pizza Away**.

| Layer | Unit | Addressing | Examples | Interview focus |
|---|---|---|---|---|
| 7 Application | Data | URL/name | HTTP, DNS, SMTP, FTP, SSH | User-facing protocols |
| 6 Presentation | Data | - | TLS, encoding, compression | Format, encryption, compression |
| 5 Session | Data | - | Session management, RPC | Start/manage/end sessions |
| 4 Transport | Segment/Datagram | Port | TCP, UDP | Reliability, ordering, ports |
| 3 Network | Packet | IP | IPv4, IPv6, ICMP, routers | Routing and logical addressing |
| 2 Data Link | Frame | MAC | Ethernet, Wi-Fi, ARP, switch | Local delivery and framing |
| 1 Physical | Bits | - | Cable, fiber, radio | Signals and media |


Interview line: data becomes segments, packets, frames, then bits while going down the stack.

### OSI Layer Roles and Key Responsibilities

| Layer | Compact role | Main responsibilities |
|---|---|---|
| 7 Application | Provides network services to user applications. | Provides services directly to the end user. It provides user interfaces and support for services such as electronic mail, remote file access and transfer, shared database management, and other distributed information services. |
| 6 Presentation | Converts data into a usable/secure format. | Translation/format conversion, compression, encryption/decryption. |
| 5 Session | Manages communication sessions between applications. | Dialog control, synchronization/checkpoints, session establishment/termination. |
| 4 Transport | Delivers complete messages from one process to another. | Port/service-point addressing, segmentation and reassembly, connection control, end-to-end flow control, error control. |
| 3 Network | Delivers packets from source host to destination host across networks. | Logical addressing using IP, routing/path selection. |
| 2 Data Link | Delivers frames from one node/hop to the next. | Framing, physical/MAC addressing, flow control, error control, access control. |
| 1 Physical | Moves individual bits from one node/hop to the next over a medium. | Bit representation as signals, data rate, bit synchronization, line configuration, physical topology, transmission mode. |

Very compact line: Physical moves **bits**, Data Link moves **frames**, Network moves **packets**, Transport moves **process-to-process messages**, and upper layers manage **sessions, format/security, and user services**.

## 10. TCP/IP Model

Link: [IBM - TCP/IP model](https://www.ibm.com/docs/en/aix/7.3?topic=protocol-tcpip-protocols)

| TCP/IP | OSI mapping | Examples |
|---|---|---|
| Application | Application + Presentation + Session | HTTP, DNS, SMTP, FTP, SSH |
| Transport | Transport | TCP, UDP |
| Internet | Network | IP, ICMP |
| Network Access | Data Link + Physical | Ethernet, Wi-Fi |

OA angle: real internet discussions usually use TCP/IP, interviews often ask OSI for conceptual clarity.

## 11. How a Packet Travels

Link: [Cloudflare - What is a packet?](https://www.cloudflare.com/learning/network-layer/what-is-a-packet/)

Example: browser opens `https://example.com`.

1. Browser checks cache and asks DNS for IP.
2. If destination is outside LAN, host sends to default gateway.
3. Host uses ARP to find gateway MAC address.
4. Data is split and wrapped: HTTP data -> TCP segment -> IP packet -> Ethernet/Wi-Fi frame.
5. Switch forwards frame by MAC inside LAN.
6. Router removes old frame, checks destination IP, chooses next hop, adds new frame.
7. At server, headers are removed upward and application receives request.
8. Response follows similar path back.

Important: IP source/destination mostly stay end-to-end; MAC source/destination change at every hop.

## 12. Ethernet

Link: [IEEE 802.3 Ethernet overview](https://standards.ieee.org/standard/802_3-2022.html)

- Data-link technology used in wired LANs.
- Uses frames with source MAC, destination MAC, type/length, payload, and FCS.
- Modern switched Ethernet avoids most collision issues.
- Old shared Ethernet used CSMA/CD.

### CSMA/CD and CSMA/CA

**CSMA:** Carrier Sense Multiple Access. Devices first sense/check the medium before transmitting.

| Method | Full form | Used in | Main idea |
|---|---|---|---|
| CSMA/CD | Carrier Sense Multiple Access with Collision Detection | Old shared Ethernet | Detect collision after it happens, stop, wait random time, retransmit |
| CSMA/CA | Carrier Sense Multiple Access with Collision Avoidance | Wi-Fi | Avoid collision before sending using waiting/backoff/ACK mechanisms |

### CSMA/CD Process

1. Device checks whether the cable/medium is idle.
2. If idle, it starts transmitting.
3. If collision is detected, it stops transmission.
4. It sends a jam signal so all devices know collision happened.
5. It waits for a random backoff time.
6. It retransmits later.

Interview line: CSMA/CD detects collisions; used in old half-duplex Ethernet, not modern switched full-duplex Ethernet.

### CSMA/CA Process

1. Device checks whether wireless medium is idle.
2. If busy, it waits.
3. If idle for a required time, it waits a random backoff.
4. It transmits the frame.
5. Receiver sends ACK if frame is received correctly.
6. If ACK is not received, sender assumes collision/loss and retries.

Optional RTS/CTS flow:
- Sender sends RTS: Request To Send.
- Receiver replies CTS: Clear To Send.
- Other devices stay silent for that duration.

Interview line: Wi-Fi uses CSMA/CA because collision detection is difficult in wireless networks.

## 13. Wi-Fi Basics

Link: [Wi-Fi Alliance - Wi-Fi](https://www.wi-fi.org/discover-wi-fi)

- Wireless LAN based on IEEE 802.11.
- Uses radio channels, access points, SSID, authentication, and encryption.
- Common bands: 2.4 GHz has longer range; 5/6 GHz usually faster and less crowded.
- Security: prefer WPA2/WPA3; avoid open/WEP networks.

## 14. MAC Address and NIC

Link: [Cloudflare - MAC address](https://www.cloudflare.com/learning/network-layer/what-is-a-mac-address/)

- **NIC:** network interface card/controller.
- **MAC address:** hardware/data-link address, usually 48 bits.
- Used for local network delivery.
- Switches learn MAC-to-port mappings.

Interview line: MAC identifies interface in a local network; IP identifies logical network location.

## 15. IP Addressing

Link: [ARIN - IP addressing](https://www.arin.net/resources/guide/ipv4/)

- **IP address:** logical address used for routing.
- **IPv4:** 32-bit, dotted decimal, example `192.168.1.10`.
- **IPv6:** 128-bit, hex notation, example `2001:db8::1`.
- **Network ID:** part identifying network.
- **Host ID:** part identifying device in that network.
- **Subnet mask/CIDR:** tells how many bits are network bits, example `/24`.

Common private IPv4 ranges:

| Range | CIDR |
|---|---|
| 10.0.0.0 - 10.255.255.255 | `10.0.0.0/8` |
| 172.16.0.0 - 172.31.255.255 | `172.16.0.0/12` |
| 192.168.0.0 - 192.168.255.255 | `192.168.0.0/16` |

## 16. Subnetting

Link: [Cisco - IP addressing and subnetting](https://www.cisco.com/c/en/us/support/docs/ip/routing-information-protocol-rip/13788-3.html)

- Subnetting divides one network into smaller networks.
- CIDR `/n` means first `n` bits are network bits.
- Usable hosts in IPv4 subnet: `2^(host bits) - 2`, except special cases.
- `/24` has 256 total addresses, 254 usable.
- `/30` has 4 total addresses, 2 usable; common for point-to-point.

Quick formulas:

- Block size = `256 - subnet mask octet`.
- Number of subnets = `2^(borrowed bits)`.
- Hosts per subnet = `2^(remaining host bits) - 2`.

## 17. Public vs Private IP

Link: [RFC 1918 - Private address allocation](https://www.rfc-editor.org/rfc/rfc1918)

- **Public IP:** globally routable on internet.
- **Private IP:** used inside local networks, not directly routable on public internet.
- NAT maps private addresses to public address/ports.

Interview line: your laptop often has private IP; your router/ISP exposes public IP.

## 18. NAT

Link: [Cloudflare - NAT](https://www.cloudflare.com/learning/network-layer/what-is-nat/)

- **NAT:** Network Address Translation.
- Maps internal private IPs to external public IP.
- **SNAT:** changes source address.
- **DNAT:** changes destination address, often port forwarding.
- **PAT/NAPT:** many private hosts share one public IP using ports.

Pros: saves IPv4 addresses, hides internal structure.
Cons: breaks pure end-to-end connectivity, complicates peer-to-peer.

## 19. ARP

Link: [RFC 826 - ARP](https://www.rfc-editor.org/rfc/rfc826)

- ARP maps IPv4 address to MAC address in a LAN.
- Flow: "Who has this IP?" broadcast -> owner replies with MAC.
- Stored in ARP cache.
- Security issue: ARP spoofing/poisoning can redirect traffic.

OA line: ARP is used before sending a frame to a local next hop.

### ARP Process / ARP Transmission

Example: Host A wants to send data to IP `192.168.1.20` in the same LAN.

1. Host A checks its ARP cache for `192.168.1.20`.
2. If not found, Host A sends an ARP Request as a broadcast frame.
3. Broadcast MAC is `FF:FF:FF:FF:FF:FF`, so all LAN devices receive it.
4. Only the device with IP `192.168.1.20` replies.
5. That device sends an ARP Reply containing its MAC address.
6. Host A stores the IP-to-MAC mapping in ARP cache.
7. Host A sends the actual data frame to that MAC address.

If destination is outside the LAN:
- Host A does not ARP for the final destination.
- It ARPs for the default gateway/router MAC address.
- The router then forwards the packet toward the destination network.

Interview line: ARP resolves next-hop IP to next-hop MAC, not always final destination IP.

## 20. DNS

Link: [Cloudflare - DNS](https://www.cloudflare.com/learning/dns/what-is-dns/)

- DNS maps domain names to IP addresses and other records.
- Common records:
  - `A`: domain to IPv4.
  - `AAAA`: domain to IPv6.
  - `CNAME`: alias to another name.
  - `MX`: mail server.
  - `TXT`: text metadata, SPF/DKIM verification.
  - `NS`: authoritative name servers.
- Recursive resolver finds answer from root -> TLD -> authoritative server.
- TTL controls cache lifetime.

Interview line: DNS reduces human memory burden and enables indirection/load balancing.

## 21. URL, URI, HTTP, HTTPS

Link: [MDN - HTTP overview](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/Overview)

- **URI:** identifier for a resource.
- **URL:** URI that also gives location/access method.
- **HTTP:** stateless application-layer protocol.
- **HTTPS:** HTTP over TLS; provides encryption, integrity, and authentication.

URL parts: `scheme://host:port/path?query#fragment`

Example: `https://api.example.com:443/users?id=7#profile`

## 22. HTTP Methods

Link: [MDN - HTTP methods](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Methods)

| Method | Use | Safe | Idempotent |
|---|---|---|---|
| GET | Read resource | Yes | Yes |
| POST | Create/process | No | No |
| PUT | Replace resource | No | Yes |
| PATCH | Partial update | No | Usually no |
| DELETE | Delete resource | No | Yes |
| HEAD | Headers only | Yes | Yes |
| OPTIONS | Supported operations/CORS | Yes | Yes |

Interview line: idempotent means repeating the same request has the same final effect.

## 23. HTTP Status Codes

Link: [MDN - HTTP response status codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status)

- `1xx`: informational.
- `2xx`: success. `200 OK`, `201 Created`, `204 No Content`.
- `3xx`: redirection. `301`, `302`, `304 Not Modified`.
- `4xx`: client error. `400`, `401`, `403`, `404`, `409`, `429`.
- `5xx`: server error. `500`, `502`, `503`, `504`.

## 24. HTTP/1.1, HTTP/2, HTTP/3

Link: [Cloudflare - HTTP/3](https://www.cloudflare.com/learning/performance/what-is-http3/)

- **HTTP/1.1:** persistent connections, but head-of-line blocking at request level.
- **HTTP/2:** multiplexing, header compression, binary framing over TCP.
- **HTTP/3:** runs over QUIC/UDP; reduces TCP head-of-line blocking and improves connection setup.

OA angle: HTTP/3 uses QUIC over UDP, not TCP.

## 25. SSL/TLS

Link: [Cloudflare - TLS](https://www.cloudflare.com/learning/ssl/transport-layer-security-tls/)

- TLS secures communication using encryption, integrity checks, and certificates.
- Server proves identity using certificate signed by CA.
- Handshake negotiates keys and cipher suite.
- HTTPS = HTTP over TLS.

Interview line: TLS does not hide destination IP, but protects request content from network observers.

* SSL — Secure Sockets Layer
    * Older cryptographic protocol used to secure communication over a network.
    * Deprecated due to security vulnerabilities.
* TLS — Transport Layer Security
    * Modern successor to SSL.
    * Provides encryption, authentication, and data integrity for network communication (e.g., HTTPS).
    
## 26. TCP

Link: [RFC 9293 - TCP](https://www.rfc-editor.org/rfc/rfc9293)

- Connection-oriented, reliable, ordered byte stream.
- Uses sequence numbers, acknowledgments, retransmission, flow control, congestion control.
- Good for web pages, file transfer, email, SSH.

### TCP 3-Way Handshake

1. Client sends `SYN`.
2. Server replies `SYN-ACK`.
3. Client sends `ACK`.

Purpose: both sides agree on initial sequence numbers and confirm reachability.

### TCP Termination

- Usually uses `FIN` and `ACK` from both sides.
- `TIME_WAIT` ensures delayed packets do not affect future connections.

## 27. UDP

Link: [RFC 768 - UDP](https://www.rfc-editor.org/rfc/rfc768)

- Connectionless, no built-in reliability, no ordering guarantee.
- Low overhead and fast.
- Used in DNS, VoIP, gaming, streaming, QUIC.

TCP vs UDP:

| TCP | UDP |
|---|---|
| Reliable | Best effort |
| Ordered | Unordered |
| Connection-oriented | Connectionless |
| Higher overhead | Lower overhead |
| Web/file/email | DNS/streaming/gaming |

## 28. Ports

Link: [IANA - Service name and port registry](https://www.iana.org/assignments/service-names-port-numbers/service-names-port-numbers.xhtml)

Common ports:

| Port | Protocol |
|---:|---|
| 20/21 | FTP |
| 22 | SSH |
| 23 | Telnet |
| 25 | SMTP |
| 53 | DNS |
| 67/68 | DHCP |
| 80 | HTTP |
| 110 | POP3 |
| 123 | NTP |
| 143 | IMAP |
| 161/162 | SNMP |
| 443 | HTTPS |
| 3306 | MySQL |
| 5432 | PostgreSQL |
| 6379 | Redis |

Interview line: IP finds the host; port finds the process/service.

## 29. Flow Control, Error Control, Congestion Control

Link: [Cloudflare - TCP/IP](https://www.cloudflare.com/learning/ddos/glossary/tcp-ip/)

- **Flow control:** prevents sender from overwhelming receiver. TCP uses receive window.
- **Error control:** detects and recovers from loss/corruption using checksum, ACK, retransmission.
- **Congestion control:** prevents overwhelming network. TCP uses algorithms such as slow start and congestion avoidance.

Difference: flow control protects receiver; congestion control protects network.


### ARQ: Automatic Repeat Request

ARQ is an error-control method where the receiver sends ACK/NAK and the sender retransmits lost or damaged frames/packets.

| ARQ type | Idea | Advantage | Disadvantage |
|---|---|---|---|
| Stop-and-Wait ARQ | Send one frame, wait for ACK, then send next. | Simple | Slow, poor link utilization |
| Go-Back-N ARQ | Sender can send multiple frames using a window; if one frame is lost, retransmit that frame and all frames after it. | Better than stop-and-wait | Wastes bandwidth by resending correctly received later frames |
| Selective Repeat ARQ | Sender retransmits only the lost/damaged frames. | Most efficient | More complex; receiver needs buffering |

### Window Size Formulas

Let:
- `m` = number of bits used for the sequence number.
- Sequence numbers range from `0` to `2^m - 1`.

| Protocol | Sender Window Size | Receiver Window Size |
|---|---:|---:|
| Stop-and-Wait | 1 | 1 |
| Go-Back-N | `2^m - 1` | 1 |
| Selective Repeat | `2^(m-1)` | `2^(m-1)` |

Why?
- **Go-Back-N:** Receiver only accepts the next expected frame, so sender can use up to `2^m - 1` outstanding frames.
- **Selective Repeat:** Sender and receiver windows must each be at most `2^(m-1)` to avoid ambiguity after sequence number wraparound.

Interview line:
- Stop-and-Wait → SW = 1, RW = 1
- Go-Back-N → SW = `2^m - 1`, RW = 1
- Selective Repeat → SW = RW = `2^(m-1)`

### Stop-and-Wait ARQ Process

1. Sender sends one frame.
2. Sender starts timer.
3. Receiver checks frame.
4. If correct, receiver sends ACK.
5. If ACK is lost or timer expires, sender retransmits the frame.

### Go-Back-N ARQ Process

1. Sender sends multiple frames within sender window.
2. Receiver accepts only frames in order.
3. If frame `k` is lost, receiver discards later out-of-order frames.
4. Sender retransmits frame `k` and all frames after it.

### Selective Repeat ARQ Process

1. Sender sends multiple frames within window.
2. Receiver accepts and buffers out-of-order correct frames.
3. Receiver asks/reports only missing frames.
4. Sender retransmits only missing/damaged frames.

Interview line: Stop-and-wait is simplest, Go-Back-N resends from the lost frame onward, Selective Repeat resends only the lost frames.

## 29A. Error Detection Techniques

<!-- Assume this section exists or is referenced for insertion point -->

## 29B. Hamming Distance and Hamming Code

### Hamming Distance

**Hamming Distance** is the number of bit positions in which two binary strings differ.

Example:
```
101101
100001
```
Different positions = **2**, so Hamming Distance = **2**.

Uses:
- Detect transmission errors.
- Design error-correcting codes.

Interview line: Higher Hamming distance means better error detection and correction capability.

### Hamming Code

Hamming Code is an error-correcting code that can detect and correct single-bit errors.

Important points:
- Adds redundant parity bits.
- Parity bits are placed at positions 1, 2, 4, 8, 16... (powers of two).
- Receiver recomputes parity bits.
- Wrong parity combination directly identifies the erroneous bit.
- Can correct one-bit errors and detect (but not reliably correct) many double-bit errors.

Parity bit formula:

If `m` = data bits and `r` = parity bits,

`2^r >= m + r + 1`

Interview line: CRC mainly detects errors, whereas Hamming Code can both detect and correct single-bit errors.

### Error Detection vs Error Correction

| Technique | Detect | Correct |
|---|---:|---:|
| Parity | Yes | No |
| Checksum | Yes | No |
| CRC | Yes | No |
| Hamming Code | Yes | Yes (single-bit) |

## 30. Multiplexing

Link: [GeeksforGeeks - Multiplexing](https://www.geeksforgeeks.org/computer-networks/multiplexing-in-computer-network/)

- Multiple signals/connections share one medium/channel.
- **TDM:** time slots.
- **FDM:** frequency bands.
- **WDM:** wavelengths in fiber.
- **Transport multiplexing:** many app connections share one IP via ports.

## 31. Routing

Link: [Cloudflare - Routing](https://www.cloudflare.com/learning/network-layer/what-is-routing/)

- Routing chooses path for packets between networks.
- **Static routing:** manually configured, simple but not adaptive.
- **Default routing:** route used when no specific route matches.
- **Dynamic routing:** routers exchange routes using protocols.
- Common algorithms:
  - Distance vector: chooses by distance/cost, example RIP.
  - Link state: each router builds network map, example OSPF.
  - Path vector: policy-based internet routing, example BGP.

Interview line: longest prefix match chooses the most specific route.

## 32. Switches and VLAN

Link: [Cisco - VLAN](https://www.cisco.com/c/en/us/support/docs/lan-switching/vlan/10023-3.html)

- Switch forwards frames using MAC address table.
- **Unmanaged switch:** plug-and-play.
- **Managed switch:** VLANs, monitoring, configuration.
- **Layer 3 switch:** can route between VLANs.
- **VLAN:** logical LAN segmentation on same physical switch infrastructure.

Why VLANs: isolation, security, broadcast control, department separation.

## 33. Gateway vs Router

Link: [Cloudflare - What is a router?](https://www.cloudflare.com/learning/network-layer/what-is-a-router/)

- **Router:** forwards packets between IP networks.
- **Default gateway:** router a host sends traffic to when destination is outside local subnet.
- **Gateway:** broader term; may translate protocols or connect different network types.

## 34. Modem vs Router

Link: [Cisco - Modem/router basics](https://www.cisco.com/c/en/us/solutions/small-business/resource-center/networking/how-to-set-up-a-network.html)

- **Modem:** connects home/office to ISP medium.
- **Router:** creates local network, routes traffic, often does NAT/DHCP/firewall.
- Many home devices combine modem, router, switch, and Wi-Fi access point.

## 35. DHCP

Link: [Microsoft - DHCP overview](https://learn.microsoft.com/en-us/windows-server/networking/technologies/dhcp/dhcp-top)

- Dynamically assigns IP configuration to hosts.
- Provides IP address, subnet mask, default gateway, DNS server, lease time.
- DORA flow:
  1. Discover
  2. Offer
  3. Request
  4. Acknowledge

## 36. ICMP

Link: [Cloudflare - ICMP](https://www.cloudflare.com/learning/ddos/glossary/internet-control-message-protocol-icmp/)

- Used for network diagnostics and error messages.
- `ping` uses ICMP echo request/reply.
- `traceroute` relies on TTL expiry ICMP messages.
- ICMP is not TCP/UDP; it sits with IP at network layer.

## 37. Common Networking Commands

Link: [Microsoft - TCP/IP utilities](https://learn.microsoft.com/en-us/troubleshoot/windows-client/networking/tcp-ip-utilities)

| Command | Use |
|---|---|
| `ping host` | Check reachability/latency |
| `traceroute host` / `tracert host` | Show route hops |
| `nslookup domain` | DNS lookup |
| `dig domain` | Detailed DNS lookup |
| `ipconfig` / `ifconfig` / `ip addr` | Show IP config |
| `netstat -an` | Show connections/listening ports |
| `ss -tulnp` | Modern Linux socket listing |
| `arp -a` | Show ARP cache |
| `curl -I url` | Fetch HTTP headers |
| `telnet host port` / `nc -vz host port` | Test TCP port reachability |

Interview debugging flow: check IP config -> ping gateway -> ping public IP -> DNS lookup -> HTTP curl.

## 38. API Gateway

Link: [AWS - API Gateway pattern](https://aws.amazon.com/api-gateway/)

- Single entry point for backend APIs.
- Handles routing, auth, rate limiting, request transformation, logging, caching.
- Useful in microservices to avoid exposing each service directly.

Interview line: API gateway is application-level entry control; load balancer distributes traffic.

## 39. Reverse Proxy

Link: [Cloudflare - Reverse proxy](https://www.cloudflare.com/learning/cdn/glossary/reverse-proxy/)

- Server-side proxy that sits in front of origin servers.
- Can provide TLS termination, caching, compression, routing, WAF, hiding origin IP.
- Examples: Nginx, Envoy, HAProxy, Cloudflare.

Forward proxy represents clients; reverse proxy represents servers.

## 40. Load Balancer

Link: [Cloudflare - Load balancing](https://www.cloudflare.com/learning/performance/what-is-load-balancing/)

- Distributes requests across multiple backend servers.
- Algorithms: round-robin, least connections, weighted, IP hash.
- Types:
  - L4: uses IP/port, faster, less app awareness.
  - L7: understands HTTP paths/headers/cookies, more flexible.
- Health checks remove unhealthy servers.

## 41. Caching

Link: [MDN - HTTP caching](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/Caching)

- Caching stores copies of data to reduce latency and backend load.
- Places: browser cache, CDN, reverse proxy, app cache, database cache.
- HTTP headers: `Cache-Control`, `ETag`, `Last-Modified`, `Expires`.
- Cache policies:
  - Write-through: write cache and DB together.
  - Write-back: write cache first, DB later.
  - Cache-aside: app manually reads/writes cache.

Interview risk: stale data, invalidation complexity, cache stampede.

## 42. CDN

Link: [Cloudflare - CDN](https://www.cloudflare.com/learning/cdn/what-is-a-cdn/)

- CDN stores content near users at edge locations.
- Reduces latency, origin load, and bandwidth cost.
- Good for static assets, images, videos, public APIs with cacheable responses.

## 43. Horizontal vs Vertical Scaling

Link: [AWS - Horizontal vs vertical scaling](https://aws.amazon.com/compare/the-difference-between-horizontal-and-vertical-scaling/)

- **Vertical scaling:** bigger machine. Simple, limited, can be expensive.
- **Horizontal scaling:** more machines. More available/scalable, needs load balancing and stateless design.

Interview line: web tiers scale horizontally; single DB often starts vertical then uses replicas/sharding.

## 44. Performance vs Scalability

Link: [Microsoft - Performance efficiency](https://learn.microsoft.com/en-us/azure/well-architected/performance-efficiency/)

- **Performance:** how fast system handles one/current workload.
- **Scalability:** ability to handle increased workload by adding resources.
- A fast single server may be performant but not scalable.

## 45. Latency vs Throughput

Link: [Cloudflare - Latency](https://www.cloudflare.com/learning/performance/glossary/what-is-latency/)

- **Latency:** time for one operation/request.
- **Throughput:** number of operations/amount of data per time.
- Batching may improve throughput but increase latency.

### Important Network Delays

Total delay is mainly the sum of transmission, propagation, processing, and queuing delay.

| Delay type | Meaning | Formula/Idea |
|---|---|---|
| Transmission delay | Time to push all bits of a packet onto the link. | Packet size / Bandwidth |
| Propagation delay | Time for first bit to travel from sender to receiver. | Distance / Propagation speed |
| Processing delay | Time routers/hosts take to check headers, errors, routing table, etc. | Depends on device speed |
| Queuing delay | Time packet waits in router/switch queue before transmission. | Depends on congestion |

Example:
- Large file on slow link → high transmission delay.
- Long distance like India to US → high propagation delay.
- Busy router → high queuing delay.

Interview line: transmission delay depends on packet size and bandwidth; propagation delay depends on distance and signal speed.

## 46. REST API vs HTTP API

Link: [Red Hat - REST API](https://www.redhat.com/en/topics/api/what-is-a-rest-api)

- **HTTP API:** any API using HTTP.
- **REST API:** architectural style using resources, URLs, statelessness, standard methods, cacheability.
- REST is usually implemented over HTTP, but not every HTTP API is truly REST.

REST checklist: resource nouns, correct methods/status codes, stateless requests, cache semantics.

## 47. Containers, Containerization, Virtualization

Link: [Docker - What is a container?](https://www.docker.com/resources/what-container/)

- **Container:** packaged app plus dependencies sharing host OS kernel.
- **VM:** full guest OS on hypervisor.
- Containers are lighter and faster to start; VMs provide stronger OS-level isolation.
- Container networking often uses bridge networks, NAT, port mapping, and overlay networks.

## 48. VPN

Link: [Cloudflare - VPN](https://www.cloudflare.com/learning/access-management/what-is-a-vpn/)

- VPN creates encrypted tunnel between client and VPN server/network.
- Used for privacy on untrusted networks and remote access to private networks.
- Common protocols: IPsec, OpenVPN, WireGuard.

Interview line: VPN provider can see traffic metadata; HTTPS is still needed for end-to-end app encryption.

## 49. Firewall, ACL, AAA

Links: [Cloudflare - Firewall](https://www.cloudflare.com/learning/security/what-is-a-firewall/), [Cisco - AAA](https://www.cisco.com/c/en/us/support/docs/security-vpn/remote-authentication-dial-user-service-radius/13838-10.html)

- **Firewall:** allows/blocks traffic based on rules.
- **ACL:** ordered allow/deny rules, often based on source/destination/protocol/port.
- **AAA:**
  - Authentication: who are you?
  - Authorization: what can you access?
  - Accounting: what did you do?

Interview line: authentication verifies identity; authorization checks permissions.

## 50. Intranet, Extranet, Internet

Link: [Cloudflare - Internet](https://www.cloudflare.com/learning/network-layer/what-is-the-internet/)

- **Internet:** public global network.
- **Intranet:** private internal organization network.
- **Extranet:** controlled external access to part of an intranet for partners/vendors.

## 51. Virtual Circuit vs Datagram Network

Link: [GeeksforGeeks - Virtual circuit and datagram networks](https://www.geeksforgeeks.org/computer-networks/difference-between-virtual-circuit-and-datagram-networks/)

- **Virtual circuit:** path is established before data transfer; packets follow same path. Example idea: circuit-like WAN/MPLS.
- **Datagram:** each packet routed independently. Internet IP is datagram-based.

Tradeoff: virtual circuit has setup overhead but predictable path; datagram is flexible and robust.

## 52. Peer-to-Peer Sharing

Link: [Cloudflare - Peer-to-peer network](https://www.cloudflare.com/learning/network-layer/what-is-peer-to-peer/)

- Peers share resources directly without central server for every transfer.
- Examples: BitTorrent, local file sharing, some WebRTC flows.
- Challenges: NAT traversal, trust, discovery, inconsistent availability.

## 53. File Transfer

Link: [MDN - HTTP range requests](https://developer.mozilla.org/en-US/docs/Web/HTTP/Guides/Range_requests)

- File transfer may use FTP, SFTP, SCP, HTTP(S), SMB, or BitTorrent.
- Important ideas: chunking, checksums, resume, compression, encryption.
- **FTP:** old, separate control/data connections, not secure by default.
- **SFTP/SCP:** secure over SSH.
- **HTTP download:** common for web, supports caching and range requests.

## 54. Email Flow

Link: [Cloudflare - Email security protocols](https://www.cloudflare.com/learning/email-security/dmarc-dkim-spf/)

- User sends mail via SMTP to outgoing mail server.
- Server checks recipient domain MX record in DNS.
- Mail is transferred using SMTP to recipient mail server.
- Recipient reads using IMAP/POP3/webmail.
- Security records:
  - SPF: allowed sending servers.
  - DKIM: cryptographic signature.
  - DMARC: policy using SPF/DKIM results.

Ports: SMTP `25/587/465`, IMAP `143/993`, POP3 `110/995`.

## 55. Bluetooth

Link: [Bluetooth - How Bluetooth technology works](https://www.bluetooth.com/learn-about-bluetooth/tech-overview/)

- Short-range wireless technology for PAN.
- Uses 2.4 GHz band.
- Supports pairing, profiles, low-energy mode.
- Common uses: headphones, keyboards, IoT sensors.

## 56. Hotspot

Link: [Android - Share a mobile connection by hotspot](https://support.google.com/android/answer/9059108)

- A hotspot shares one device's internet connection with others using Wi-Fi/Bluetooth/USB.
- Phone usually acts like router: DHCP, NAT, local Wi-Fi network.
- Security should use WPA2/WPA3 password.

## 57. 2G, 3G, 4G, 5G

Link: [Qualcomm - 5G explained](https://www.qualcomm.com/5g/what-is-5g)

- **2G:** digital voice/SMS, low data.
- **3G:** mobile internet, higher data than 2G.
- **4G/LTE:** broadband mobile data, lower latency.
- **5G:** higher bandwidth, lower latency, massive device support, network slicing.

Interview angle: each generation improves capacity, speed, latency, and use cases.

## 58. LiFi vs Wi-Fi

Link: [IEEE - LiFi](https://standards.ieee.org/beyond-standards/lifi/)

- **Wi-Fi:** radio waves, passes through walls, mature ecosystem.
- **LiFi:** visible/infrared light communication, high speed potential, line-of-sight/room-limited.
- LiFi can reduce RF interference but needs light path.

## 59. SONET

Link: [Cisco - SONET basics](https://www.cisco.com/c/en/us/support/docs/optical/synchronous-optical-network-sonet/29000-sonet.html)

- Synchronous Optical Network.
- Standard for high-speed data over optical fiber.
- Used historically in telecom carrier backbone networks.

## 60. ATM

Link: [Britannica - ATM network technology](https://www.britannica.com/technology/ATM-communications)

- Asynchronous Transfer Mode.
- Uses fixed-size 53-byte cells.
- Designed for voice/video/data with QoS.
- Mostly legacy, but useful interview concept for virtual circuits and fixed cell switching.

## 61. VIP in Networking

Link: [F5 - Virtual IP address](https://www.f5.com/glossary/virtual-ip-address)

- **VIP:** Virtual IP address.
- Often represents a load-balanced service rather than one physical server.
- Clients connect to VIP; load balancer forwards to healthy backend pool.

## 62. Security Essentials

Link: [OWASP - Transport Layer Protection](https://cheatsheetseries.owasp.org/cheatsheets/Transport_Layer_Security_Cheat_Sheet.html)

- Use HTTPS/TLS for sensitive traffic.
- Never send passwords/tokens over plain HTTP.
- Hash and salt passwords; do not encrypt passwords for storage.
- Use firewalls/security groups with least privilege.
- Validate certificates; avoid self-signed certs in production unless pinned/trusted.
- Common attacks:
  - DoS/DDoS: overwhelm service.
  - MITM: intercept/modify traffic.
  - DNS spoofing: poison name resolution.
  - ARP spoofing: redirect LAN traffic.
  - Port scanning: find exposed services.
  - Packet sniffing: read unencrypted traffic.

## 63. OA/Interview Must-Know Comparisons

| Question | Short answer |
|---|---|
| TCP vs UDP | TCP reliable/ordered; UDP faster/best-effort |
| HTTP vs HTTPS | HTTPS = HTTP over TLS |
| Router vs Switch | Router uses IP between networks; switch uses MAC in LAN |
| MAC vs IP | MAC local hardware address; IP logical routable address |
| Hub vs Switch | Hub broadcasts all; switch forwards intelligently |
| Public vs Private IP | Public internet-routable; private internal only |
| NAT vs Proxy | NAT changes IP/port at network edge; proxy terminates and forwards app/client requests |
| Forward proxy vs Reverse proxy | Forward protects/represents clients; reverse protects/represents servers |
| Authentication vs Authorization | Who are you vs what can you do |
| Latency vs Throughput | Delay for one request vs work/data per time |
| Vertical vs Horizontal scaling | Bigger machine vs more machines |
| Stateless vs Stateful | No stored session dependency vs server remembers session |
| DNS vs ARP | DNS name to IP; ARP IP to MAC in LAN |
| GET vs POST | GET reads and is safe; POST creates/processes and changes state |
| 401 vs 403 | 401 unauthenticated; 403 authenticated but not allowed |
| 502 vs 504 | Bad gateway response vs gateway timeout |

## 64. Common Interview Flows to Explain

### What happens when you enter a URL?

Link: [AWS - What happens when you type a URL](https://aws.amazon.com/blogs/mobile/what-happens-when-you-type-a-url-into-your-browser/)

1. Browser parses URL.
2. Checks cache/HSTS.
3. DNS resolves domain to IP.
4. TCP connection is created.
5. TLS handshake happens for HTTPS.
6. HTTP request is sent.
7. Server/reverse proxy/load balancer routes request.
8. App generates response.
9. Browser receives HTML/CSS/JS/assets and renders page.

### How does DNS resolution work?

A domain consists of:

```text
www.google.com
│    │      │
│    │      └── TLD (Top-Level Domain): .com, .org, .in, .edu
│    └──────── Domain Name: google
└───────────── Subdomain/Host: www
```

DNS Resolution Flow:

1. Browser/OS checks its local DNS cache.
2. If not found, it asks a **Recursive Resolver** (ISP, Google DNS, Cloudflare DNS). This resolver performs the lookup on behalf of the client.
3. The resolver asks a **Root DNS Server**, which replies: "I don't know the IP, but ask the `.com` TLD server."
4. The **TLD (Top-Level Domain) Server** replies: "For `google.com`, ask this Authoritative DNS Server."
5. The **Authoritative DNS Server** returns the actual IP address of `google.com`.
6. The resolver returns the IP to the browser, and the browser connects to that IP.

Flow:

```text
Browser
   ↓
Recursive Resolver
   ↓
Root Server
   ↓
TLD Server (.com)
   ↓
Authoritative DNS Server
   ↓
IP Address
```

Interview line: The Root server points to the TLD server, the TLD server points to the Authoritative server, and the Authoritative server returns the final IP address.

### How does HTTPS protect data?

Link: [Cloudflare - HTTPS](https://www.cloudflare.com/learning/ssl/what-is-https/)

1. Client connects to server.
2. Server sends certificate.
3. Client verifies certificate chain and hostname.
4. Client/server negotiate keys.
5. Encrypted HTTP data flows over TLS.

### How does a request reach a private server behind NAT?

Link: [Cloudflare - Port forwarding](https://www.cloudflare.com/learning/network-layer/what-is-port-forwarding/)

- Outbound: NAT stores mapping from private IP:port to public IP:port.
- Inbound reply: NAT table maps response back to internal host.
- External inbound request needs port forwarding, reverse proxy, VPN, or public endpoint.

## 65. Quick Revision Checklist

- Can you explain digital-to-digital and analog-to-analog transmission?
- Can you compare CSMA/CD and CSMA/CA?
- Can you explain the ARP request/reply process?
- Can you compare transmission, propagation, processing, and queuing delay?
- Can you compare Stop-and-Wait, Go-Back-N, and Selective Repeat ARQ?

- Can you draw OSI and give one protocol per layer?
- Can you explain TCP handshake and why UDP exists?
- Can you explain DNS, ARP, DHCP, NAT in one minute each?
- Can you debug "website not opening" using commands?
- Can you compare router/switch/hub/gateway/modem?
- Can you explain what changes at every hop: MAC changes, IP mostly remains.
- Can you explain HTTP methods, status codes, cookies, cache headers?
- Can you explain CDN, load balancer, reverse proxy, API gateway?
- Can you explain latency, throughput, scalability, performance?
- Can you list common ports: 22, 53, 80, 443, 25, 110, 143, 3306, 5432, 6379?

### Advanced Topics to Revise

- Data encoding: NRZ, NRZI, Manchester, Differential Manchester
- Digital-to-Analog: ASK, FSK, PSK, QAM
- Analog-to-Digital: PCM, Delta Modulation
- Nyquist theorem and Shannon capacity theorem
- FDM, TDM, WDM (with diagrams)
- Circuit Switching vs Packet Switching vs Message Switching
- Virtual Circuit vs Datagram (packet flow)
- IPv4 header and IPv6 header
- Fragmentation and MTU
- TTL
- ICMP message types
- RIP vs OSPF vs BGP
- Distance Vector vs Link State routing
- Sliding Window (with diagrams)
- TCP Congestion Control: Slow Start, AIMD, Fast Retransmit, Fast Recovery
- Socket programming basics
- Sockets, Ports, Ephemeral Ports
- Broadcast Domains vs Collision Domains
- VLAN Tagging (802.1Q)
- Spanning Tree Protocol (STP)
- Proxy ARP
- RARP and BOOTP
- DHCP DORA (with packet-flow diagram)
- CORS
- HTTP vs WebSocket
- Long Polling vs SSE vs WebSocket
- Cookies vs Sessions vs JWT
- Forward Proxy vs Reverse Proxy (architecture)
- Load Balancing Algorithms
- Nagle's Algorithm
- MSS vs MTU
- QoS (Quality of Service)
- MIME Types
- RSA, Diffie–Hellman, Digital Signatures
- OSI vs TCP/IP comparison table
- Can you calculate Hamming Distance?
- Can you explain Hamming Code and parity-bit placement?
- Can you compare CRC, checksum, parity, and Hamming Code?

## 66. Last-Minute Port Table

| Service | Port | TCP/UDP |
|---|---:|---|
| FTP data/control | 20/21 | TCP |
| SSH/SFTP/SCP | 22 | TCP |
| Telnet | 23 | TCP |
| SMTP | 25 | TCP |
| DNS | 53 | UDP/TCP |
| DHCP | 67/68 | UDP |
| HTTP | 80 | TCP |
| POP3 | 110 | TCP |
| NTP | 123 | UDP |
| IMAP | 143 | TCP |
| SNMP | 161/162 | UDP |
| HTTPS | 443 | TCP; HTTP/3 uses UDP |
| MySQL | 3306 | TCP |
| PostgreSQL | 5432 | TCP |
| Redis | 6379 | TCP |

