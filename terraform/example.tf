provider "aws" {
    access_key = "${var.access_key}"
    secret_key = "${var.secret_key}"
    region = "${var.region}"
}

resource "aws_instance" "example" {
  ami           = "ami-13be557e"
  instance_type = "t2.micro"
  subnet_id = "subnet-fcbd7fd7"
}

resource "aws_eip" "ip" {
    instance = "${aws_instance.example.id}"
    vpc = true
}
