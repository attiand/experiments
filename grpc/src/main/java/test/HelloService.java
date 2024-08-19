package test;

import com.google.protobuf.Any;
import examples.GreeterGrpc;
import examples.HelloReply;
import examples.HelloRequest;
import examples.MyType;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;

@GrpcService
public class HelloService extends GreeterGrpc.GreeterImplBase {

	@Override
	public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
		String name = request.getName();
		String message = "Hello " + name;

		MyType myType = MyType.newBuilder().setMessage("my message").build();

		responseObserver.onNext(HelloReply.newBuilder().setMessage(message).setDetails(0, Any.pack(myType)).build());
		responseObserver.onCompleted();
	}
}